package com.emsi.Parcel.Notification.Service.repositories;

import com.emsi.Parcel.Notification.Service.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
