package org.vferrer.portfolio.manager.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.vferrer.portfolio.manager.entitties.Portfolio;

@RepositoryRestResource(collectionResourceRel = "portfolios", path = "portfolios")
public interface PortfolioRepository extends PagingAndSortingRepository<Portfolio, Long> {

	@PreAuthorize("isFullyAuthenticated && (#login==principal.username)")
	public List<Portfolio> findByOwner_Login(@Param("login") String login);
}