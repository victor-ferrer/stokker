package org.vferrer.stokker.elk;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface StockQuotationJPARepository extends PagingAndSortingRepository<StockQuotationJPA, Long> 
{
	
	public List<StockQuotationJPA> findValueByStock(@Param("ticker") String ticker);

	
	public StockQuotationJPA findTopByStockOrderByTimestampDesc(@Param("ticker") String ticker);
}
