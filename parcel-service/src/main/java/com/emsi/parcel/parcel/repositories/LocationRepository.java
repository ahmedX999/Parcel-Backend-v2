package com.emsi.parcel.parcel.repositories;

import com.emsi.parcel.parcel.entities.Location;
import com.emsi.parcel.parcel.entities.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

    List<Location> findByParcelOrderByTimestampDesc(Parcel parcel);
    Location findByParcel_Id (Long id);
}
