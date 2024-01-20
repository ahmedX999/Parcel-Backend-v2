package com.emsi.Parcel.Notification.Service.services;

import com.emsi.Parcel.Notification.Service.beans.Parcel;
import com.emsi.Parcel.Notification.Service.entities.Notification;
import com.emsi.Parcel.Notification.Service.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {


    private final ParcelServiceClient parcelServiceClient;
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;



    public <S extends Notification> S save(S entity) {
        return notificationRepository.save(entity);
    }

    public Optional<Notification> findById(Long aLong) {
        return notificationRepository.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return notificationRepository.existsById(aLong);
    }

    public long count() {
        return notificationRepository.count();
    }

    public void deleteById(Long aLong) {
        notificationRepository.deleteById(aLong);
    }

    public void deleteAll() {
        notificationRepository.deleteAll();
    }


    public List<Parcel> getAllParcels() {
        return parcelServiceClient.getAllParcels();
    }


    public void updateEmailSentStatus(String trackingNumber) {
        parcelServiceClient.updateEmailSentStatus(trackingNumber);
    }

    public String sendEmail(Long parcelId) {
        Parcel parcel = parcelServiceClient.getParcelById(parcelId);
        String trackingNumber = parcel.getTrackingNumber();
        // check if status is Done
        if(parcel.getStatus().equals("Done")){


                if(!parcel.isEmailSent()){
                     emailService.sendEmail(parcel.getUser().getEmail(), "Parcel Delivered Successfully", "Your parcel with tracking number " + parcel.getTrackingNumber() + " has been delivered successfully!");
                     updateEmailSentStatus(trackingNumber);
                     return "Email sent successfully";
                }
                else{
                      return "The email is already sent";
                }

        }

        else{
         return "The parcel is not delivred yet";
        }

        }


}
