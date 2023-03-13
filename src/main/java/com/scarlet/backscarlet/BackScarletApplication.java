package com.scarlet.backscarlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class BackScarletApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackScarletApplication.class, args);
    }

}
