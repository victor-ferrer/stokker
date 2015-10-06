package org.vferrer.portfolio.manager.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.vferrer.portfolio.manager.entitties.Portfolio;

@RepositoryRestResource(collectionResourceRel = "portfolios", path = "portfolios")
public interface PortfolioRepository extends PagingAndSortingRepository<Portfolio, Long> {


}