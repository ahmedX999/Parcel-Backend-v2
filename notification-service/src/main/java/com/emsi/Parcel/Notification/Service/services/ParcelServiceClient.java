package com.emsi.Parcel.Notification.Service.services;

import com.emsi.Parcel.Notification.Service.beans.Parcel;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.stereotype.Repository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "parcel-service")
public interface ParcelServiceClient {

    @GetMapping("/api/parcels")
    List<Parcel> getAllParcels();

    @GetMapping("/api/parcels/{parcelId}")
    Parcel getParcelById(@PathVariable Long parcelId);

    @PostMapping("/api/parcels/emailSent/{trackingNumber}")
    void updateEmailSentStatus(@PathVariable String trackingNumber);
}
