package demo.domain;

import org.springframework.data.geo.Point;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by colt on 12/3/17.
 */


@RepositoryRestResource(path = "supplyLocations")
public interface SupplyLocationRepository extends PagingAndSortingRepository<SupplyLocation, Long> {
    @RestResource(rel = "by-location")
    SupplyLocation findFirstByLocationNear(@Param("location") Point location);
}
