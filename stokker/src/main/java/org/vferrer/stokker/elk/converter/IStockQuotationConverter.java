package org.vferrer.stokker.elk.converter;

import org.vferrer.stokker.elk.StockQuotation;

public interface IStockQuotationConverter<T> 
{
	public StockQuotation convertLiveCSVToStockQuotation(T input);
	
	/**
	 * Data from Yahoo Finance comes in this CSV format:
	 *  - Date
	 *  - Open
	 *  - High
	 *  - Low
	 *  - Close
	 *  - Volume
	 *  - Adjusted Close
	 *  
	 *  Note: Ticker field comes in the message header
	 * @param input
	 * @return
	 */
	public StockQuotation convertHistoricalCSVToStockQuotation(String ticker, T input);
}
