package org.vferrer.portfolio.manager.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.vferrer.portfolio.manager.entitties.Position;
import org.vferrer.portfolio.manager.entitties.Stock;

@RepositoryRestResource(collectionResourceRel = "stocks", path = "stocks")
public interface StockRepository extends PagingAndSortingRepository<Stock, Long> {

	List<Position> findByMarket(String market);

}