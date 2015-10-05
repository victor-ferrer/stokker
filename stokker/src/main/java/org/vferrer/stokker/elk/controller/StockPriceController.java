package org.vferrer.stokker.elk.controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vferrer.stokker.elk.ELKClient;
import org.vferrer.stokker.elk.StockQuotation;

// TODO: This should be refactored in order to use a more RESTful approach
// However, we have to wait until the bug with the elastic search repositories is fixed in Spring data REST
@RestController
public class StockPriceController 
{
	
	@Autowired
	private ELKClient elkClient;
	

	// TODO: This should be refactored in order to use a more RESTful approach
	// However, we have to wait until the bug with the elastic search repositories is fixed in Spring data REST
	@RequestMapping(value = "/getPrices", produces = "application/json")
	public List<StockQuotation> getDailyPrices(String ticker, String date)
	{
		List<StockQuotation> toReturn = new ArrayList<>();
		toReturn.add(buildQuotation(ticker,elkClient.getAvgPriceByStockAndDate(ticker, DateTime.parse(date))));
		return toReturn;
	}

	private StockQuotation buildQuotation(String stock, Double value) 
	{
		StockQuotation quotation = new StockQuotation();
		quotation.setValue(value);
		quotation.setStock(stock);
		return quotation; 
	}

}
