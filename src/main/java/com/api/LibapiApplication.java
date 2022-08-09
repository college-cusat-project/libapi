package com.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
public class LibapiApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(LibapiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("this is testing");
    }
}
