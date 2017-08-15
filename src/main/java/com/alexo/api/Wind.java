package com.alexo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class representing the key:value pairs for the wind class
 * <code>"wind":{"speed":3.1,"deg":350}</code>
 *
 * Created by vagrant on 13/07/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind {

    /**
     * Speed of wind
     */
    @JsonProperty
    int speed;

    /**
     * Degree of wind
     */
    @JsonProperty("deg")
    double degree;

    @JsonProperty
    double gust;

}
