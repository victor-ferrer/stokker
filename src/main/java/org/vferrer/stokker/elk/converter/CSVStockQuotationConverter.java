package org.vferrer.stokker.elk.converter;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.elasticsearch.common.joda.time.DateTime;
import org.elasticsearch.common.joda.time.format.DateTimeFormatter;
import org.elasticsearch.common.joda.time.format.DateTimeFormatterBuilder;
import org.springframework.stereotype.Component;
import org.vferrer.stokker.elk.StockQuotation;

@Component
public class CSVStockQuotationConverter implements IStockQuotationConverter<String> {

	private DateTimeFormatter inputFormatter;
	
	private static final int STOCK_COLUMN = 0;
	private static final int DATE_COLUMN = 1;
	private static final int PRICE_COLUMN = 2;
	
	public CSVStockQuotationConverter() 
	{
		DateTimeFormatterBuilder formatterBuilder = new DateTimeFormatterBuilder();
		inputFormatter = formatterBuilder.appendPattern("dd/MM/yyyy HH:mm:ss").toFormatter();	
	}
	
	@Override
	public StockQuotation converToStockQuotation(String input) {
 		String[] chunks = input.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		
		if (chunks.length != 3)
		{
			throw new IllegalArgumentException("Invalid CSV stock quotation: " + input);
		}
			
		StockQuotation quotation = new StockQuotation();
		quotation.setStock(chunks[STOCK_COLUMN].replaceAll("\"", ""));
		quotation.setValue(Double.parseDouble(chunks[PRICE_COLUMN].replaceAll("\"", "").replaceAll(",", ".") + "d"));
		
		DateTime time = DateTime.parse(chunks[DATE_COLUMN].replaceAll("\"", ""),inputFormatter);
		Calendar calendar = new GregorianCalendar(time.getYear(), time.getMonthOfYear(), time.getDayOfMonth(), time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute());
		quotation.setTimestamp(calendar);
		
		return quotation;
	}

}
