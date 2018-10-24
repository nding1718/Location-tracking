package demo.rest;

import demo.domain.SupplyLocation;
import demo.domain.SupplyLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by colt on 12/3/17.
 */

@RestController
public class SupplyLocationUploadController {

    private SupplyLocationRepository repository;

    @Autowired
    public SupplyLocationUploadController(SupplyLocationRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/bulk/supplyLocations", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<SupplyLocation> locations) {
        this.repository.save(locations);
    }
}
