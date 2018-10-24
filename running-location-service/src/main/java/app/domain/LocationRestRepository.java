package app.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by colt on 10/23/18.
 * This is an example using spring boot data rest
 */

@RepositoryRestResource(path = "locations", collectionResourceRel = "locations")
public interface LocationRestRepository extends PagingAndSortingRepository<Location, Long> {
    @RestResource(path = "runningId", rel = "by-runningId")
    Page<Location> findByUnitInfoRunningId(@Param("runningId") String runningId, Pageable pageable);
}
