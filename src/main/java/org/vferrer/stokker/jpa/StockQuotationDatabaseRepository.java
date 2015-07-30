package org.vferrer.stokker.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vferrer.stokker.elk.StockQuotation;

@Repository
public interface StockQuotationDatabaseRepository extends CrudRepository<StockQuotation, Long> 
{
    List<StockQuotation> findByStock(String stock);
}