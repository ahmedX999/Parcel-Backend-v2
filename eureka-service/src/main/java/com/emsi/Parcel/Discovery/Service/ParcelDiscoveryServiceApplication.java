package com.emsi.Parcel.Discovery.Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ParcelDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcelDiscoveryServiceApplication.class, args);
	}

}
