package com.unsplash.sdk.model;

import lombok.Value;

/**
 * Location Details.
 */
@Value
public class Location {
    /**
     * City name.
     */
    String city;
    /**
     * Country name.
     */
    String country;
    /**
     * Position details.
     */
    Position position;

    private static class Position {
        /**
         * Latitude detail.
         */
        String latitude;
        /**
         * Longitude detail.
         */
        String longitude;
    }
}
