package com.emsi.parcel.parcel.controllers;

import com.emsi.parcel.parcel.entities.Location;
import com.emsi.parcel.parcel.entities.Parcel;
import com.emsi.parcel.parcel.services.LocationService;
import com.emsi.parcel.parcel.services.ParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
@CrossOrigin(origins = "*")
public class LocationController {

    private final LocationService locationService;
    private final ParcelService parcelService;

    @GetMapping("/parcel")
    public List<Location> getLocationHistory(Parcel parcel) {
        return locationService.getLocationHistory(parcel);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        locationService.deleteById(id);
    }
    @GetMapping
    public List<Location> getAllLocationHistory() {
        return locationService.getAllLocationHistory();
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location, @RequestParam Long parcelId) {
        // Fetch the Parcel by ID
        Parcel parcel = parcelService.getParcelById(parcelId)
                .orElseThrow(() -> new RuntimeException("Parcel not found with id: " + parcelId));

        // Set the Parcel for the Location
        location.setParcel(parcel);

        // Save the Location
        return locationService.saveLocation(location);
    }

}
