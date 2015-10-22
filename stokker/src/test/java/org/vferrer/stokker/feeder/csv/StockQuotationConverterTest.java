package org.vferrer.stokker.feeder.csv;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.vferrer.stokker.elk.StockQuotation;
import org.vferrer.stokker.elk.converter.CSVStockQuotationConverter;

public class StockQuotationConverterTest {

	@Test
	public void testLiveConversion() throws ParseException {

		String csvInput = "REP.MC,23/07/2015 17:36:00,\"16,155\"";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		CSVStockQuotationConverter converter = new CSVStockQuotationConverter();
		StockQuotation result = converter.convertLiveCSVToStockQuotation(csvInput);
		
		assertEquals("Invalid parsed value",16.155d,result.getValue(), 0.0001d);
		assertEquals("Invalid parsed stock","REP.MC",result.getStock());
		assertEquals("Invalid parsed date", sdf.parse("23/07/2015 17:36:00"), result.getTimestamp().getTime());
		
	}
	
	@Test
	public void testHistoricalConversionWithHeader() throws ParseException {

		// URL for retrieving the data:
		// http://ichart.finance.yahoo.com/table.csv?s=T&amp;a=1&amp;b=1&amp;c=2015&amp;g=ds
		
		String csvInput = "2015-10-21,33.880001,33.939999,33.330002,33.599998,27215000,33.599998";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		CSVStockQuotationConverter converter = new CSVStockQuotationConverter();
		StockQuotation result = converter.convertHistoricalCSVToStockQuotation("REP.MC",csvInput);
		
		assertEquals("Invalid parsed value",33.59999,result.getValue(), 0.0001d);
		assertEquals("Invalid parsed stock","REP.MC",result.getStock());
		assertEquals("Invalid parsed date", sdf.parse("21/10/2015 00:00:00"), result.getTimestamp().getTime());
		
	}


}
