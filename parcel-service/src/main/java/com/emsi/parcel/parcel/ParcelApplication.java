package com.emsi.parcel.parcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ParcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParcelApplication.class, args);
    }

}
