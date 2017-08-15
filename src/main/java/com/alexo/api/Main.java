package com.alexo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is the main temperature reading from the JSON response, eg
 * <code>"main":{"temp":288.31,"pressure":1018,"humidity":82,"temp_min":287.15,"temp_max":289.15}</code>
 *
 * Created by vagrant on 13/07/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
    @JsonProperty
    double temp;

    @JsonProperty
    int pressure;

    @JsonProperty
    int humidity;

    @JsonProperty
    double temp_min;

    /**
     * The maximum temperature recorded.
     *
     * This field has been renamed from the JSON reponse from <code>temp_max</code> to <code>max</code>.
     */
    @JsonProperty("temp_max")
    double max;


}
