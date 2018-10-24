package demo.service.impl;

import demo.domain.SupplyLocation;
import demo.service.SupplyLocationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colt on 12/3/17.
 */
@Service
public class SupplyLocationServiceImpl implements SupplyLocationService{

    @Override
    public List<SupplyLocation> saveSupplyLocationsZipContains504(List<SupplyLocation> locations) {
        List<SupplyLocation> result = new ArrayList<>();
        for (SupplyLocation location : locations) {
            if (location.getZip().contains("504")) {
                result.add(location);
            }
        }
        return null;
    }
}
