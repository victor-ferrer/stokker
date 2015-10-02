package org.vferrer.portfolio.manager.entitties;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "positions", path = "positions")
public interface PositionRepository extends PagingAndSortingRepository<Position, Long> {

	List<Position> findByStock(@Param("stock") String stock);

}