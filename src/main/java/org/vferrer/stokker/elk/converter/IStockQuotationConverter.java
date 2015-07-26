package org.vferrer.stokker.elk.converter;

import org.vferrer.stokker.elk.StockQuotation;

public interface IStockQuotationConverter<T> 
{
	public StockQuotation converToStockQuotation(T input);
}
