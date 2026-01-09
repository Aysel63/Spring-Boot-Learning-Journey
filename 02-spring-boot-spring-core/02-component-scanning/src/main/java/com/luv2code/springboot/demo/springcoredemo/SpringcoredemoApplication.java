package com.luv2code.springboot.demo.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@SpringBootApplication(
       scanBasePackages = {"com.luv2code.springboot.demo.springcoredemo",
       "com.luv2code.util"}
)

 */
@SpringBootApplication
public class SpringcoredemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcoredemoApplication.class, args);
    }

}
