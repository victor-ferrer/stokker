package org.vferrer.stokker.elk;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogram;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogram.Bucket;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogram.Interval;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.avg.AvgBuilder;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Component;

@Component
public class ELKClient {

	// This template is configured by default in order to connect to a local
	// ES node and injected in the application context
	@Autowired
    private ElasticsearchTemplate template;

	@Value("${elasticsearch.index.name}")
	public String indexName;
	
	@Autowired	
	private StockQuotationRepository stockRepo;
	
	@PostConstruct
	public void initIndex()
	{
		// Create an index if necessary
		if (!template.indexExists(indexName)){
			template.createIndex(indexName);
		}
		
		// Tell ELK to consider StockQuotation as a entity to use
		template.putMapping(StockQuotation.class);
		template.refresh(indexName, true);
		
	}
	
	/**
	 * Indexes a single StockQuotation
	 * @param quotation
	 * @throws Exception
	 */
	public void pushToELK(StockQuotation quotation) throws Exception
	{
		stockRepo.save(quotation);	
	}

	/**
	 * Bulk indexing of a list of stock quotations for better performance
	 * @param quotationList
	 * @throws Exception
	 */
	public void pushToELK(List<StockQuotation> quotationList) throws Exception
	{
		List<IndexQuery> queries = new ArrayList<>();
		
		for (StockQuotation quotation : quotationList){
			
			IndexQuery indexQuery = new IndexQuery();
			indexQuery.setIndexName(indexName);
			indexQuery.setObject(quotation);
			queries.add(indexQuery);
		}
		
		template.bulkIndex(queries);
	}
	
	/*
	
    {
    "query": {
        "match": {
           "stock": "MAP.MC"
        }
    }, 
    
    "size": 0, 
    
 	 "aggs": {  
        "per_day": {
          "date_histogram": {
            "field": "timestamp",
            "interval": "day",
            "format": "yyyy-MM-dd"
          },
          
          "aggs": {
	            "total": {
	              	avg": {
	                "field": "value"
	              	}
	            }
          }
    	}
  	}
	 * */
	public Double getAvgPriceByStockAndDate(String stock, DateTime date)
	{
		MatchQueryBuilder queryBuilder = new MatchQueryBuilder("stock", stock);
		
		NativeSearchQuery query = new NativeSearchQuery(queryBuilder);
		
		DateHistogramBuilder perDayAggregation = new DateHistogramBuilder("per_day");
		perDayAggregation.field("timestamp");
		perDayAggregation.interval(Interval.DAY);
		perDayAggregation.format("yyyy-MM-dd");
		
		AvgBuilder avg = new AvgBuilder("total");
		avg.field("value");
		perDayAggregation.subAggregation(avg);
		
		query.addAggregation(perDayAggregation);
		
		
		Double value = template.query(query, new ResultsExtractor<Double>() {

			@Override
			public Double extract(SearchResponse response) 
			{
				DateHistogram dateHisto = (DateHistogram) response.getAggregations().asMap().get("per_day");
				
				Bucket bucketByKey = dateHisto.getBucketByKey(date.toString("yyyy-MM-dd"));
				
				if (bucketByKey == null){
					return null;
				}
				
				Avg avg = (Avg)bucketByKey.getAggregations().asMap().get("total");
				return avg.getValue();
			}
		});
	
		return value;
	}

}