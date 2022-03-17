package com.da_java.p8_trippricer;

import com.da_java.p8_trippricer.controller.TripPricerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tripPricer.Provider;
import tripPricer.TripPricer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withinPercentage;
import static org.mockito.Mockito.when;

public class TripPricerControllerUT {

    @Mock
    private TripPricer tripPricer;

    @InjectMocks
    private TripPricerController tripPricerControllerUT;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenGetPriceWithValidParametersThenReturnProviderList() {
        String apiKey = "apikey";
        String attractionId = "123e4567-e89b-12d3-a456-426614174000";
        int adults = 2;
        int children = 4;
        int nightsStay = 4;
        int rewardPoints = 123;
        Provider provider = new Provider(UUID.randomUUID(), "name", 10D);
        when(tripPricer.getPrice(apiKey, UUID.fromString(attractionId),
                adults, children, nightsStay, rewardPoints)).thenReturn(
                Collections.singletonList(provider)
        );

        ResponseEntity<List<Provider>> result = tripPricerControllerUT.getPrice(
                apiKey, attractionId, adults, children, nightsStay, rewardPoints
        );
        assertThat(result.getBody().get(0)).isEqualTo(provider);
        assertThat(result.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
    }

    @Test
    void whenGetProviderNameWithValidParametersThenReturnStringName() {
        String nameProvider = "nameProvider";
        String apiKey = "apiKey";
        int adults = 3;
        when(tripPricer.getProviderName(apiKey, adults)).thenReturn(nameProvider);

        ResponseEntity<String> result =
                tripPricerControllerUT.getProviderName(apiKey, adults);
        assertThat(result.getBody()).isEqualTo(nameProvider);
        assertThat(result.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
    }
}
