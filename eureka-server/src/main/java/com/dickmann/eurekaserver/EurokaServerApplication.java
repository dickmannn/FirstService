package com.dickmann.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import javax.swing.*;


@SpringBootApplication
@EnableEurekaServer
public class EurokaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurokaServerApplication.class, args);
    }

}
