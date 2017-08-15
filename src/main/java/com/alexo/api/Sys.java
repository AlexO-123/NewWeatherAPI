package com.alexo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is the System class from the Json response
 * <code>"sys":{"type":1,"id":5088,"message":0.0046,"country":"GB","sunrise":1500004813,"sunset":1500063117},</code>
 *
 * Created by vagrant on 13/07/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys {

    /**
     * Integer type code for the weather
     */
    @JsonProperty
    int type;

    /**
     * Id of the weather
     */
    @JsonProperty
    int id;

    /**
     * Message recorded from the weather
     */
    @JsonProperty
    double message;

    /**
     * Country code from the weather location
     */
    @JsonProperty
    String country;

    /**
     * Sunrise time recorded for the current day
     */
    @JsonProperty
    int sunrise;

    /**
     * Sunset time for the current day
     */
    @JsonProperty
    int sunset;

}
