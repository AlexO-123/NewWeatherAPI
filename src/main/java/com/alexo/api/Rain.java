package com.alexo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vagrant on 01/08/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rain {

    @JsonProperty("3h")
    String h;
}
