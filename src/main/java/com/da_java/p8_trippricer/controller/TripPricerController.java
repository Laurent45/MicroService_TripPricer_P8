package com.da_java.p8_trippricer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tripPricer.Provider;
import tripPricer.TripPricer;

import java.util.List;
import java.util.UUID;

@Api("API which provides trip price according to user preferences and rewards" +
        " points and get provider's informations.")

@RestController
@RequestMapping("/api/v1/tripPricer")
public class TripPricerController {

    private final TripPricer tripPricer;

    public TripPricerController(TripPricer tripPricer) {
        this.tripPricer = tripPricer;
    }

    @ApiOperation(value = "Get a list of provider according to the request " +
            "parameters.")
    @GetMapping("/getPrice")
    public ResponseEntity<List<Provider>> getPrice(
            @RequestParam String apikey
            , @RequestParam String attractionId
            , @RequestParam int adults
            , @RequestParam int children
            , @RequestParam int nightsStay
            , @RequestParam int rewardsPoints
    ) {
        UUID attractionUUId;
        if (adults < 0 || children < 0 || nightsStay < 0 || rewardsPoints < 0)
            return ResponseEntity.badRequest().build();
        try {
            attractionUUId = UUID.fromString(attractionId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tripPricer.getPrice(apikey, attractionUUId,
                adults, children, nightsStay, rewardsPoints));
    }

    @ApiOperation(value = "Get provider name according to api key and " +
            "the number of adults.")
    @GetMapping("/getProviderName")
    public ResponseEntity<String> getProviderName(@RequestParam String apiKey
            , @RequestParam int adults) {
        if (adults < 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tripPricer.getProviderName(apiKey, adults));
    }
}
