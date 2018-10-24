package app.service.impl;

import app.domain.Location;
import app.domain.LocationRepository;
import app.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by colt on 12/1/17.
 */

/**
 *  this Service annotation is important, it will mark this class as a service class, so that when spring IOC container will search for this kind of class.
 *  And will generate this kind of Bean (for this class, is LocationServiceImpl). And only in this situation, you can Autowired this class in the controller
 *  Or you will have a running time error tells you that you can not find bean for this class.
 *
 *  But why we don't need to do this when we Autowired the LocationRepository, that is because spring data JPA help us to create the bean.
 */
@Service
public class LocationServiceImpl implements LocationService {

    private  LocationRepository locationRepository;

    @Autowired // this is where we use constructor DI
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository =  locationRepository;
    }


    @Override
    public List<Location> saveRunningLocations(List<Location> runingLocations) {
        return locationRepository.save(runingLocations);
    }


    @Override
    public void deleteAll() {
        locationRepository.deleteAll();
    }

    @Override
    public Page<Location> findByRunnerMovementType(String movementType, Pageable pageable) {
        // value of part change the string to be an enum
        return locationRepository.findByRunnerMovementType(Location.RunnerMovementType.valueOf(movementType), pageable );
    }

    @Override
    public Page<Location> findByRunningId(String runningId, Pageable pageable) {
        return null;
    }


}
