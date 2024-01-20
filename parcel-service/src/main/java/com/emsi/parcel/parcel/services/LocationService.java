package com.emsi.parcel.parcel.services;

import com.emsi.parcel.parcel.entities.Location;
import com.emsi.parcel.parcel.entities.Parcel;
import com.emsi.parcel.parcel.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location saveLocation(Location location) {

        return locationRepository.save(location);
    }

    public List<Location> getLocationHistory(Parcel parcel) {
        return locationRepository.findByParcelOrderByTimestampDesc(parcel);
    }

    public void deleteById(Long aLong) {
        locationRepository.deleteById(aLong);
    }

    public List<Location> getAllLocationHistory() {
        return locationRepository.findAll();
    }

    public Location addLocationToParcel(Location location) {
        // Set the timestamp to the current date and time
        location.setTimestamp(new Date());

        // Save the location to the repository
        return locationRepository.save(location);
    }

}
