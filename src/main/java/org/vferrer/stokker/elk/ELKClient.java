package org.vferrer.stokker.elk;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;

import org.elasticsearch.common.joda.time.DateTime;
import org.elasticsearch.common.joda.time.format.DateTimeFormatter;
import org.elasticsearch.common.joda.time.format.DateTimeFormatterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Component;

@Component
public class ELKClient {

	@Autowired
    private ElasticsearchTemplate template;

	private DateTimeFormatter inputFormatter;

	public final static String INDEX_NAME = "stockquotations4";

	private static final int STOCK_COLUMN = 0;
	private static final int DATE_COLUMN = 1;
	private static final int PRICE_COLUMN = 2;

	
	@PostConstruct
	public void initIndex()
	{
		if (!template.indexExists(INDEX_NAME)){
			template.createIndex(INDEX_NAME);
		}
		
		// Tell ELK to consider StockQuotation as a entity to use
		template.putMapping(StockQuotation.class);
		template.refresh(INDEX_NAME, true);
		
		
		// FIXME There should be a better way to do this
		DateTimeFormatterBuilder formatterBuilder = new DateTimeFormatterBuilder();
		inputFormatter = formatterBuilder.appendDayOfMonth(2).appendLiteral("/").
									 appendMonthOfYear(2).appendLiteral("/").
									 appendYear(4, 4).appendLiteral(" ").
									 appendHourOfDay(2).appendLiteral(":").
									 appendMinuteOfHour(2).appendLiteral(":").
									 appendSecondOfMinute(2).toFormatter();
	}
	
	public String pushToELK(String quotationCSV) throws Exception
	{
		// TODO Extract this to a converter
 		String[] chunks = quotationCSV.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		
		if (chunks.length != 3)
		{
			throw new IllegalArgumentException("Invalid CSV stock quotation: " + quotationCSV);
		}
			
		StockQuotation quotation = new StockQuotation();
		quotation.setStock(chunks[STOCK_COLUMN].replaceAll("\"", ""));
		quotation.setValue(Double.parseDouble(chunks[PRICE_COLUMN].replaceAll("\"", "").replaceAll(",", ".") + "d"));
		
		
		DateTime time = DateTime.parse(chunks[DATE_COLUMN].replaceAll("\"", ""),inputFormatter);
		Calendar calendar = new GregorianCalendar(time.getYear(), time.getMonthOfYear(), time.getDayOfMonth(), time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute());
		quotation.setTimestamp(calendar);
			
		IndexQuery query = new IndexQuery();
		query.setIndexName(INDEX_NAME);
		query.setObject(quotation);
			
		return template.index(query);
	}

}