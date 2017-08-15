package com.alexo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <code>"clouds":{"all":76},</code>
 *
 * Created by vagrant on 13/07/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Clouds {

    /**
     * Represents the % of cloud
     */
    @JsonProperty
    int all;

}
