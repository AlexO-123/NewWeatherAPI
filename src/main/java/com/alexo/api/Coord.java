package com.alexo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Co-ordinates for the weather location
 * <code>"coord":{"lon":-0.13,"lat":51.51},</code>
 *
 * Created by vagrant on 13/07/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coord {

    /**
     * Longitude
     */
    @JsonProperty
    double lon;

    /**
     * Latitude
     */
    @JsonProperty
    double lat;

}
