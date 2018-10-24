package app.rest;

import app.domain.Location;
import app.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by colt on 12/1/17.
 */

@RestController
public class RunningUploadRestController {
    private LocationService locationService;

    @Autowired // check the LocationServiceImp comments to get more info about this
    public RunningUploadRestController(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     *  The ResponseStatus annotation will help us get a more detailed information about our http response(if we succeed)
     * @param locations
     */
    @RequestMapping(value = "/running", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<Location> locations) {
        this.locationService.saveRunningLocations(locations);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        locationService.deleteAll();
    }

    /**
     *  PageRequest is an Implementation of the pagable interface
     * @param movementType
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/running/{movementType}", method = RequestMethod.GET)
    public Page<Location> findByMovementType(@PathVariable String movementType,
                                             @RequestParam(name = "page", required = false) Integer page,
                                             @RequestParam(name = "size", required = false) Integer size) {
        return  locationService.findByRunnerMovementType(movementType, new PageRequest(page, size));
    }
}
