package com.emsi.Parcel.Notification.Service.controllers;

import com.emsi.Parcel.Notification.Service.beans.Parcel;
import com.emsi.Parcel.Notification.Service.services.EmailService;
import com.emsi.Parcel.Notification.Service.services.NotificationService;
import com.emsi.Parcel.Notification.Service.services.ParcelServiceClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notification")
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationService notificationService;
    private final EmailService emailService;
    private final ParcelServiceClient parcelServiceClient;


    @GetMapping("/email")
    public void sendEmail(String to, String subject, String text) {
        to="ahmedaittaleb999@gmail.com";
        subject="Parcel Delivered Successfully";
        text="Your parcel with tracking number F123456 has been delivered successfully!";
        emailService.sendEmail(to, subject, text);
    }

    @GetMapping("/parcels")
    public List<Parcel> getAllParcels() {
        return notificationService.getAllParcels();
    }
    @GetMapping()
    public String HelloWorld(){
        return "Hello World";
    }



    @GetMapping("/sendEmailToClient/{parcelId}")
    public String sendEmailToClient(@PathVariable Long parcelId) {
        return notificationService.sendEmail(parcelId);
    }
    @PostMapping("/updateEmailSent/{trackingNumber}")
    public void updateEmailSentStatus(@PathVariable("trackingNumber") String trackingNumber) {
       notificationService.updateEmailSentStatus(trackingNumber);
    }

}
