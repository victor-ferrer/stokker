package org.vferrer.stokker.elk;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Component;
import org.vferrer.stokker.jpa.StockQuotationDatabaseRepository;

@Component
public class ELKClient {

	// This template is configured by default in order to connect to a local
	// ES node and injected in the application context
	@Autowired
    private ElasticsearchTemplate template;

	@Value("${elasticsearch.index.name}")
	public String indexName;
	
	@Autowired	
	private StockQuotationDatabaseRepository stockRepo;
	
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
	
	public String pushToELK(StockQuotation quotation) throws Exception
	{
		stockRepo.save(quotation);	
		
		// Create the Query POJO targeting our index and with our CSV payload
		IndexQuery query = new IndexQuery();
		query.setIndexName(indexName);
		query.setObject(quotation);
			
		return template.index(query);
	}
}