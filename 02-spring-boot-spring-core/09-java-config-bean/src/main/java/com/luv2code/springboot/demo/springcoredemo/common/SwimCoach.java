package com.luv2code.springboot.demo.springcoredemo.common;

public class SwimCoach implements Coach {

    public SwimCoach(){
        System.out.println(" in constructor: " + getClass().getSimpleName());}


    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters as a warm up";
    }
}
