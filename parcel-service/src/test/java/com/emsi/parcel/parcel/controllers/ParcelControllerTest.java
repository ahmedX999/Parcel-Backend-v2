package com.emsi.parcel.parcel.controllers;

import com.emsi.parcel.parcel.entities.Parcel;
import com.emsi.parcel.parcel.services.ParcelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParcelControllerTest {

    @Mock
    private ParcelService parcelService;

    @InjectMocks
    private ParcelController parcelController;

    @Test
    void saveParcel() {
        Parcel parcel = new Parcel();
        when(parcelService.saveParcel(parcel)).thenReturn(parcel);

        Parcel result = parcelController.saveParcel(parcel);

        assertEquals(parcel, result);
        verify(parcelService, times(1)).saveParcel(parcel);
    }

    @Test
    void getAllParcels() {
        Parcel parcel1 = new Parcel();
        Parcel parcel2 = new Parcel();
        List<Parcel> parcels = Arrays.asList(parcel1, parcel2);
        when(parcelService.getAllParcels()).thenReturn(parcels);

        List<Parcel> result = parcelController.getAllParcels();

        assertEquals(2, result.size());
        verify(parcelService, times(1)).getAllParcels();
    }

    @Test
    void findByTrackingNumber() {
        String trackingNumber = "ABC123";
        Parcel parcel = new Parcel();
        when(parcelService.findByTrackingNumber(trackingNumber)).thenReturn(parcel);

        Parcel result = parcelController.findByTrackingNumber(trackingNumber);

        assertEquals(parcel, result);
        verify(parcelService, times(1)).findByTrackingNumber(trackingNumber);
    }

    @Test
    void deleteById() {
        Long id = 1L;
        doNothing().when(parcelService).deleteById(id);

        parcelController.deleteById(id);

        verify(parcelService, times(1)).deleteById(id);
    }

    @Test
    void updateStatus() {
        Long id = 1L;
        String newStatus = "DELIVERED";
        Map<String, String> statusMap = Map.of("status", newStatus);

        ResponseEntity<String> responseEntity = parcelController.updateStatus(id, statusMap);

        assertEquals("Status updated successfully", responseEntity.getBody());
        verify(parcelService, times(1)).updateStatus(id, newStatus);
    }
}
