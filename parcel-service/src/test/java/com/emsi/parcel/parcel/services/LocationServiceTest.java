package com.emsi.parcel.parcel.services;

import com.emsi.parcel.parcel.entities.Location;
import com.emsi.parcel.parcel.entities.Parcel;
import com.emsi.parcel.parcel.repositories.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    @Test
    void saveLocation() {
        Location location = new Location();
        when(locationRepository.save(location)).thenReturn(location);

        Location savedLocation = locationService.saveLocation(location);

        assertNotNull(savedLocation);
        assertEquals(location, savedLocation);
        verify(locationRepository, times(1)).save(location);
    }

    @Test
    void getLocationHistory() {
        Parcel parcel = new Parcel();
        Location location1 = new Location();
        Location location2 = new Location();
        List<Location> locations = Arrays.asList(location1, location2);
        when(locationRepository.findByParcelOrderByTimestampDesc(parcel)).thenReturn(locations);

        List<Location> result = locationService.getLocationHistory(parcel);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(locationRepository, times(1)).findByParcelOrderByTimestampDesc(parcel);
    }

    @Test
    void deleteById() {
        Long locationId = 1L;
        doNothing().when(locationRepository).deleteById(locationId);

        assertDoesNotThrow(() -> locationService.deleteById(locationId));
        verify(locationRepository, times(1)).deleteById(locationId);
    }

    @Test
    void getAllLocationHistory() {
        Location location1 = new Location();
        Location location2 = new Location();
        List<Location> locations = Arrays.asList(location1, location2);
        when(locationRepository.findAll()).thenReturn(locations);

        List<Location> result = locationService.getAllLocationHistory();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(locationRepository, times(1)).findAll();
    }

    @Test
    void addLocationToParcel() {
        Location location = new Location();
        location.setParcel(new Parcel()); // Assuming Parcel is set in the location
        Date timestampBeforeSave = location.getTimestamp();

        when(locationRepository.save(location)).thenReturn(location);

        Location savedLocation = locationService.addLocationToParcel(location);

        assertNotNull(savedLocation);
        assertNotNull(savedLocation.getTimestamp());
        assertNotEquals(timestampBeforeSave, savedLocation.getTimestamp());
        verify(locationRepository, times(1)).save(location);
    }
}
