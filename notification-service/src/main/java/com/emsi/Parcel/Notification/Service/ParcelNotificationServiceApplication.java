package com.emsi.Parcel.Notification.Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ParcelNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcelNotificationServiceApplication.class, args);
	}

}
