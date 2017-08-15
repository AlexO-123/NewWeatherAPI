package com.alexo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the entire Json response for Weather
 * Created by vagrant on 13/07/17.
 */

/**
 <code>
    "coord":{
        "lon":-0.13,
        "lat":51.51
    },
    "weather":[{
        "id":803,
        "main":"Clouds",
        "description":"broken clouds",
        "icon":"04d"
    }],
    "base":"stations",
    "main":{
        "temp":288.46,
        "pressure":1025,
        "humidity":67,
        "temp_min":287.15,
        "temp_max":290.15
    },
    "visibility":10000,
    "wind":{
        "speed":3.1,
        "deg":350
    },
    "clouds":{
        "all":76
    },
    "dt":1500274200,
    "sys":{
        "type":1,
        "id":5091,
        "message":0.0057,
        "country":"GB",
        "sunrise":1500264232,
        "sunset":1500322128
    },
    "id":2643743,
    "name":"London",
    "cod":200
 </code>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherAPI {
    @JsonProperty
    Coord coord;

    @JsonProperty
    List<Weather> weather = new ArrayList<Weather>();

    @JsonProperty
    String base;

    @JsonProperty
    Main main;

    @JsonProperty
    int visibility;

    @JsonProperty
    Rain rain;

    @JsonProperty
    Wind wind;

    @JsonProperty
    Clouds clouds;

    @JsonProperty
    int dt;

    @JsonProperty
    Sys sys;

//    @MongoObjectId
//    @MongoId
    @JsonProperty
    int id;

    @JsonProperty
    String name;

    @JsonProperty
    int cod;

    /*@Override
    public String toString() {
        return "Co-ordinates: " + coord + " \r\n" +
                "Weather: " + coord + " \r\n" +
                "Base: " + coord + " \r\n" +
                "Main: " + coord + " \r\n" +
                "Visibility: " + coord + " \r\n" +
                "Wind: " + coord + " \r\n" +
                "Clouds: " + coord + " \r\n" +
                "DT: " + coord + " \r\n" +
                "System " + coord + " \r\n" +
                "ID " + coord + " \r\n" +
                "Name " + coord + " \r\n" +
                "Cod " + coord;
    }*/
}
