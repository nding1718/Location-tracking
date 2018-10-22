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
