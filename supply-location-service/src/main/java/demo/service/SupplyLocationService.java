package demo.service;

import demo.domain.SupplyLocation;

import java.util.List;

/**
 * Created by colt on 12/3/17.
 */
public interface SupplyLocationService {
    List<SupplyLocation> saveSupplyLocationsZipContains504(List<SupplyLocation> locations);
}
