package com.emsi.parcel.parcel.repositories;

import com.emsi.parcel.parcel.entities.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel,Long> {

    Parcel findByTrackingNumber(String trackingNumber);


}
