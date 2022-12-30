package com.example.demojstreeproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



@SpringBootApplication
public class DemoJsTreeProjectApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoJsTreeProjectApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoJsTreeProjectApplication.class, args);
        System.out.println("Hola World.");
    }

}
