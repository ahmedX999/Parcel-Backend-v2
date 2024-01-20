package com.emsi.parcel.parcel.services;

import com.emsi.parcel.parcel.entities.Parcel;
import com.emsi.parcel.parcel.repositories.ParcelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParcelServiceTest {

    @Mock
    private ParcelRepository parcelRepository;

    @InjectMocks
    private ParcelService parcelService;

    @Test
    void saveParcel() {
        Parcel parcel = new Parcel();
        when(parcelRepository.save(parcel)).thenReturn(parcel);

        Parcel savedParcel = parcelService.saveParcel(parcel);

        assertNotNull(savedParcel);
        assertEquals(parcel, savedParcel);
        verify(parcelRepository, times(1)).save(parcel);
    }

    @Test
    void getAllParcels() {
        List<Parcel> parcels = Arrays.asList(new Parcel(), new Parcel());
        when(parcelRepository.findAll()).thenReturn(parcels);

        List<Parcel> result = parcelService.getAllParcels();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(parcelRepository, times(1)).findAll();
    }

    @Test
    void findByTrackingNumber() {
        String trackingNumber = "ABC123";
        Parcel parcel = new Parcel();
        when(parcelRepository.findByTrackingNumber(trackingNumber)).thenReturn(parcel);

        Parcel result = parcelService.findByTrackingNumber(trackingNumber);

        assertNotNull(result);
        assertEquals(parcel, result);
        verify(parcelRepository, times(1)).findByTrackingNumber(trackingNumber);
    }

    @Test
    void deleteById() {
        Long parcelId = 1L;
        doNothing().when(parcelRepository).deleteById(parcelId);

        assertDoesNotThrow(() -> parcelService.deleteById(parcelId));
        verify(parcelRepository, times(1)).deleteById(parcelId);
    }

    @Test
    void getParcelById() {
        Long parcelId = 1L;
        Parcel parcel = new Parcel();
        when(parcelRepository.findById(parcelId)).thenReturn(Optional.of(parcel));

        Optional<Parcel> result = parcelService.getParcelById(parcelId);

        assertTrue(result.isPresent());
        assertEquals(parcel, result.get());
        verify(parcelRepository, times(1)).findById(parcelId);
    }

    @Test
    void updateStatus() {
        Long parcelId = 1L;
        String newStatus = "DELIVERED";
        Parcel existingParcel = new Parcel();
        when(parcelRepository.findById(parcelId)).thenReturn(Optional.of(existingParcel));
        when(parcelRepository.save(existingParcel)).thenReturn(existingParcel);

        assertDoesNotThrow(() -> parcelService.updateStatus(parcelId, newStatus));
        assertEquals(newStatus, existingParcel.getStatus());
        verify(parcelRepository, times(1)).findById(parcelId);
        verify(parcelRepository, times(1)).save(existingParcel);
    }

    @Test
    void updateStatusParcelNotFound() {
        Long parcelId = 1L;
        String newStatus = "DELIVERED";
        when(parcelRepository.findById(parcelId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> parcelService.updateStatus(parcelId, newStatus));
        verify(parcelRepository, times(1)).findById(parcelId);
        verify(parcelRepository, never()).save(any());
    }
}
