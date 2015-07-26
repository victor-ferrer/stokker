package org.vferrer.stokker.elk;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Component;
import org.vferrer.stokker.elk.converter.IStockQuotationConverter;

@Component
public class ELKClient {

	// This template is configured by default in order to connect to a local
	// ES node and injected in the application context
	@Autowired
    private ElasticsearchTemplate template;

	// This will be the main index to work with, which perhaps would be 
	// better off in a configuration file
	public final static String INDEX_NAME = "stockquotations";
	
	@Autowired	
	private IStockQuotationConverter<String> stockConverter;
	
	@PostConstruct
	public void initIndex()
	{
		// Create an index if necessary
		if (!template.indexExists(INDEX_NAME)){
			template.createIndex(INDEX_NAME);
		}
		
		// Tell ELK to consider StockQuotation as a entity to use
		template.putMapping(StockQuotation.class);
		template.refresh(INDEX_NAME, true);
	}
	
	public String pushToELK(String quotationCSV) throws Exception
	{
		// Convert our CSV to an entity valid for ES
		StockQuotation quotation = stockConverter.converToStockQuotation(quotationCSV);
			
		// Create the Query POJO targeting our index and with our CSV payload
		IndexQuery query = new IndexQuery();
		query.setIndexName(INDEX_NAME);
		query.setObject(quotation);
			
		return template.index(query);
	}
}