package org.vferrer.stokker.feeder.csv;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.vferrer.stokker.elk.StockQuotation;
import org.vferrer.stokker.elk.converter.CSVStockQuotationConverter;

public class StockQuotationConverterTest {

	@Test
	public void test() throws ParseException {

		String csvInput = "REP.MC,23/07/2015 17:36:00,\"16,155\"";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		CSVStockQuotationConverter converter = new CSVStockQuotationConverter();
		StockQuotation result = converter.converToStockQuotation(csvInput);
		
		assertEquals("Invalid parsed value",16.155d,result.getValue(), 0.0001d);
		assertEquals("Invalid parsed stock","REP.MC",result.getStock());
		assertEquals("Invalid parsed date", sdf.parse("23/07/2015 17:36:00"), result.getTimestamp().getTime());
		
	}

}
