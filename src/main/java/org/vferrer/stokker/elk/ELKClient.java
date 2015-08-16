package org.vferrer.stokker.elk;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.index.query.TermFilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
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
	
	public void pushToELK(StockQuotation quotation) throws Exception
	{
		stockRepo.save(quotation);	
	}

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

	
	
	public List<StockQuotation> search(String stockName) {
		
		NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
		
		TermFilterBuilder filterBuilder = new TermFilterBuilder("stock", stockName);
		
		SearchQuery searchQuery = builder.withIndices(indexName)
										 .withFields("stock","value","timestamp")
										 .withFilter(filterBuilder)
										 .build();
		
		List<StockQuotation> quotationsList = template.queryForList(searchQuery, StockQuotation.class);
		
		return quotationsList;
	}
}