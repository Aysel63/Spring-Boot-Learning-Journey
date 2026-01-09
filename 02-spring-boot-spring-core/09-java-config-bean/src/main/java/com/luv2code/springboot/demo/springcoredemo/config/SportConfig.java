package com.luv2code.springboot.demo.springcoredemo.config;

import com.luv2code.springboot.demo.springcoredemo.common.Coach;
import com.luv2code.springboot.demo.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic")
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
