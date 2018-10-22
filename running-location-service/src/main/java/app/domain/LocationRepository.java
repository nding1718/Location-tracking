package app.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by colt on 12/1/17.
 */

@RepositoryRestResource(path = "locations")
public interface LocationRepository extends JpaRepository<Location, Long> { // second parameter is id
    @RestResource(path = "runners")
    Page<Location> findByRunnerMovementType(@Param("movementType") Location.RunnerMovementType movementType, Pageable pageable);
    //localhost:8080/locations/search/customers?customername=..
    @RestResource(path = "customers")
    Page<Location> findByUnitInfoCustomerName(@Param("customerName") String customerName, Pageable pageable);
}
