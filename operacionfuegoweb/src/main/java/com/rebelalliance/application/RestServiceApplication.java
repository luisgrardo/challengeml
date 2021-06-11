package com.rebelalliance.application;



import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.rebelalliance.controller.SateliteController;
import com.rebelalliance.service.impl.SateliteServiceImpl;

@SpringBootApplication
@ComponentScan(basePackageClasses = SateliteController.class)
@ComponentScan(basePackageClasses = SateliteServiceImpl.class)
public class RestServiceApplication {

    public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(RestServiceApplication.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "5000"));
        app.run(args);
    }

}


