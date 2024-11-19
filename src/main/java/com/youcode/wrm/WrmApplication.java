package com.youcode.wrm;

import com.youcode.wrm.seeder.VisitorSeeder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WrmApplication {

    private VisitorSeeder visitorSeeder;
    public static void main(String[] args) {
        SpringApplication.run(WrmApplication.class, args);
    }



}
