package org.vferrer.stokker.elk.converter;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.springframework.stereotype.Component;
import org.vferrer.stokker.elk.StockQuotation;

@Component
public class CSVStockQuotationConverter implements IStockQuotationConverter<String> {

	private DateTimeFormatter liveInputFormatter, historicalnputFormatter;
	
	private static final int STOCK_COLUMN = 0;
	private static final int DATE_COLUMN = 1;
	private static final int PRICE_COLUMN = 2;
	
	 /* Data from Yahoo Finance comes in this CSV format:
		 *  - Date
		 *  - Open
		 *  - High
		 *  - Low
		 *  - Close
		 *  - Volume
		 *  - Adjusted Close
	*/
	private static final int HISTORICAL_DATE_COLUMN = 0;
	private static final int HISTORICAL_OPEN_COLUMN = 1;
	private static final int HISTORICAL_HIGH_COLUMN = 2;
	private static final int HISTORICAL_LOW_COLUMN = 3;
	private static final int HISTORICAL_CLOSE_COLUMN = 4;
	private static final int HISTORICAL_VOLUME_COLUMN = 5;
	
	
	public CSVStockQuotationConverter() 
	{
		DateTimeFormatterBuilder formatterBuilder = new DateTimeFormatterBuilder();
		liveInputFormatter = formatterBuilder.appendPattern("dd/MM/yyyy HH:mm:ss").toFormatter();
		formatterBuilder.clear();
		historicalnputFormatter = formatterBuilder.appendPattern("yy-MM-dd").toFormatter();
	}
	
	@Override
	public StockQuotation convertLiveCSVToStockQuotation(String input) {
 		String[] chunks = input.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		
		if (chunks.length != 3)
		{
			throw new IllegalArgumentException("Invalid CSV stock quotation: " + input);
		}
			
		StockQuotation quotation = new StockQuotation();
		quotation.setStock(chunks[STOCK_COLUMN].replaceAll("\"", ""));
		quotation.setValue(Double.parseDouble(chunks[PRICE_COLUMN].replaceAll("\"", "").replaceAll(",", ".") + "d"));
		
		DateTime time = DateTime.parse(chunks[DATE_COLUMN].replaceAll("\"", ""),liveInputFormatter);
		Calendar calendar = new GregorianCalendar(time.getYear(), time.getMonthOfYear() -1, time.getDayOfMonth(), time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute());
		quotation.setTimestamp(calendar);
		
		return quotation;
	}

	/*
	 * (non-Javadoc)
	 * @see org.vferrer.stokker.elk.converter.IStockQuotationConverter#convertHistoricalCSVToStockQuotation(java.lang.Object)
	 */
	@Override
	public StockQuotation convertHistoricalCSVToStockQuotation(String ticker, String input)
	{
 		String[] chunks = input.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
 		
		if (chunks.length != 7)
		{
			throw new IllegalArgumentException("Invalid CSV stock quotation: " + input);
		}
			
		StockQuotation quotation = new StockQuotation();
		quotation.setStock(ticker);
		quotation.setValue(Double.parseDouble(chunks[HISTORICAL_CLOSE_COLUMN].replaceAll("\"", "").replaceAll(",", ".") + "d"));
		
		quotation.setHighValue(Double.parseDouble(chunks[HISTORICAL_HIGH_COLUMN].replaceAll("\"", "").replaceAll(",", ".") + "d"));
		quotation.setLowValue(Double.parseDouble(chunks[HISTORICAL_LOW_COLUMN].replaceAll("\"", "").replaceAll(",", ".") + "d"));
		quotation.setOpenValue(Double.parseDouble(chunks[HISTORICAL_OPEN_COLUMN].replaceAll("\"", "").replaceAll(",", ".") + "d"));
		quotation.setVolume(Double.parseDouble(chunks[HISTORICAL_VOLUME_COLUMN].replaceAll("\"", "").replaceAll(",", ".") + "d"));
		
		DateTime time = DateTime.parse(chunks[HISTORICAL_DATE_COLUMN].replaceAll("\"", ""),historicalnputFormatter);
		Calendar calendar = new GregorianCalendar(time.getYear(), time.getMonthOfYear() -1, time.getDayOfMonth(), time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute());
		quotation.setTimestamp(calendar);
		
		return quotation;
	}

}
