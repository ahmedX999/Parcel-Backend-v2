package com.emsi.Parcel.Notification.Service.beans;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Parcel {

    private Long id;
    private String trackingNumber;
    private String destination;
    private String status;
    private User user;
    private boolean isEmailSent;


}
