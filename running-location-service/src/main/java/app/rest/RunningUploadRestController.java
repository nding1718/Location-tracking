package app.rest;

import app.domain.Location;
import app.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by colt on 12/1/17.
 */

@RestController
public class RunningUploadRestController {
    private LocationService locationService;

    @Autowired
    public RunningUploadRestController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "/running", method = RequestMethod.POST)
    public void upload(@RequestBody List<Location> locations) {
        this.locationService.saveRunningLocations(locations);
    }

    @RequestMapping(value = "/running/{movementType}", method = RequestMethod.GET)
    public Page<Location> findByMovementType(@PathVariable String movementType,
                                             @RequestParam(name = "page") int page,
                                             @RequestParam(name = "size") int size) {
        return  locationService.findByRunnerMovementType(movementType, new PageRequest(page, size));
    }
}
