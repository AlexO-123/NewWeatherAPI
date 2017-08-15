package com.alexo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The weather array
 * <code>"weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04d"}]</code>
 *
 * Created by vagrant on 13/07/17.
 */
public class Weather {
    @JsonProperty
    int id;

    @JsonProperty
    String main;

    @JsonProperty
    String description;

    @JsonProperty
    String icon;

}
