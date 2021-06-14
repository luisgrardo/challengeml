package com.rebelalliance.application;



import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.rebelalliance.controller.SateliteController;
import com.rebelalliance.service.impl.SateliteServiceImpl;

/**
 * @author Luis Gerardo Espinosa Sampayo
 *
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = SateliteController.class)
@ComponentScan(basePackageClasses = SateliteServiceImpl.class)
public class RestServiceApplication {

    /**
     * 
     * Clase que construlle el constexto SpringBootApplication
     * 
     * se cambia al puerto 5000 para AWS
     * @param args
     */
    public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(RestServiceApplication.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "5000"));
        app.run(args);
    }

}


