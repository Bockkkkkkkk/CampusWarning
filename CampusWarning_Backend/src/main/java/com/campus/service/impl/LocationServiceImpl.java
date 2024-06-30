package com.campus.service.impl;

import com.campus.mapper.LocationMapper;
import com.campus.pojo.Location;
import com.campus.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationMapper locationMapper;

    @Autowired
    public LocationServiceImpl(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }

    @Override
    public Location getLocationByCode(String locationCode) {
        return locationMapper.selectLocationByCode(locationCode);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationMapper.getAllLocations();
    }

    @Override
    public void addLocation(Location location) {
        locationMapper.insertLocation(location);
    }

    @Override
    public boolean updateLocation(Location location) {
        return locationMapper.updateLocation(location);
    }

    @Override
    public boolean deleteLocation(String locationCode) {
        return locationMapper.deleteLocation(locationCode);
    }
}