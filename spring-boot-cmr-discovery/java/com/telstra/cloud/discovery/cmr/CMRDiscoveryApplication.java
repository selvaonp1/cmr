package com.cmr.cloud.discovery.cmr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CMRDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CMRDiscoveryApplication.class, args);
	}
}
