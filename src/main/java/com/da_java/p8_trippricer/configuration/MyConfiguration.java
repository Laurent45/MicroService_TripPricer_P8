package com.da_java.p8_trippricer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tripPricer.TripPricer;

@Configuration
public class MyConfiguration {

    @Bean
    public TripPricer getTripPricer() {
        return new TripPricer();
    }
}
