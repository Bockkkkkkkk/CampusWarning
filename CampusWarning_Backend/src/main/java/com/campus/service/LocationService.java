package com.campus.service;

import com.campus.pojo.Location;

import java.util.List;

public interface LocationService {
    Location getLocationByCode(String locationCode);
    List<Location> getAllLocations();
    void addLocation(Location location);
    boolean updateLocation(Location location);
    boolean deleteLocation(String locationCode);
}