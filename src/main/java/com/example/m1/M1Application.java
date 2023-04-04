package com.example.m1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class M1Application {

    public static void main(String[] args) {
        SpringApplication.run(M1Application.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
        return args -> {
            Address address = new Address("Kazakhstan","AKT","Aktobe");
            String email = "maratpyn@gmail.com";
            Student student = new Student(
                  "Marat",
                  "Yerkebayev",
                    email,
                    Gender.MALE,
                  address,
                  List.of("Computer Science"),
                  BigDecimal.TEN,
                  LocalDateTime.now()
          );
            repository.findStudentByEmail(email).ifPresentOrElse(s -> {
                        System.out.println(s + " already exists");
                    }
            , () -> {
                        System.out.println("Inserting student " + student);
                        repository.insert(student);
                    });
        };
    }

}
