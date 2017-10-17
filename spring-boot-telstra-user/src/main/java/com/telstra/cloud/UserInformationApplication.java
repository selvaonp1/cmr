package com.telstra.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import com.netflix.discovery.EurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserInformationApplication {

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(UserInformationApplication.class, args);
	}

}
