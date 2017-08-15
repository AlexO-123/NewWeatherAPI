package com.alexo.resources;

import com.alexo.api.WeatherAPI;
import com.alexo.weatherConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class retrieves the Json from the given URI, and maps it to a POJO
 * This POJO is returned to the screen for the user to view the weather

 * Created by vagrant on 13/07/17.
 */
@Path("/weather")
public class RetrieveWeather {

    private static Logger logger = LoggerFactory.getLogger(RetrieveWeather.class.getName());

    private final ObjectMapper objectMapper = new ObjectMapper();
    //Instance of the POJO
    private WeatherAPI weatherAPI = null;

    private String timeStampDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
    private String timeStampTime = new SimpleDateFormat("HHmmss").format(new Date());

    weatherConfiguration weatherConfiguration;

    /*
         * Create a mongoDB instance, name db
         * Create a collection - table -
         * Insert our Json object to the collection
         */
    private Mongo mongo = new Mongo();
    private DB db = mongo.getDB("weatherDB1");

    private DBCollection collection = db.getCollection("weatherCollection1");

    /**
     * Basic constructor
     */
    public RetrieveWeather() {
        logger.debug("WeatherAPI Service online and ready");

        //logger.debug(String.valueOf(weatherConfiguration.getTimestamp()));
    }

    /**
     * Queries DB for given location
     * Maps Json to POJO and stores in DB if location not in DB
     * If location is found, determine whether we need to update the record or display DB record
     * @param location is the location for the Weather - Defaults to Swansea if none given
     * @return WeatherAPI is the POJO for the weather
     */
    @Path("/retrieve")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public WeatherAPI exampleMethod(
            @DefaultValue("Swansea") @QueryParam("q") String location) {

        logger.debug("Location: " + location);

        /*
        Our query to search for a record in the db - looking for ID that matches the location
         */
        BasicDBObject query = new BasicDBObject();
        query.put("_id", location);
        DBCursor cursor = collection.find(query);

        /*
        If we find a record, we first check the;
            Date - if not the same date, connect to URL and update DB - else we check the;
                Time - if difference in time greater than CACHE_TIME, connect to URL and update DB - else retrieve record from DB
         */
        if (cursor.hasNext()) {
            if (cursor.next().get("date").equals(timeStampDate)) {

                int storedTime = Integer.parseInt(cursor.curr().get("time").toString());
                int currentTime = Integer.parseInt(timeStampTime);
                int elapsedTime  = currentTime - storedTime;

                int CACHE_TIME = 10000;
                /*
                We don't really need to do this IF branch - however, possible fix for time going back an hour and time differences
                 */
                if (currentTime < storedTime) {

                    logger.debug("Update DB");
                    retrieveWeather(location);
                    mongoDBModifyer(location, query);

                } else if (elapsedTime < CACHE_TIME) {

                    logger.debug("CACHED");
                    logger.debug(cursor.curr().toString());

                    try {
                        weatherAPI = objectMapper.readValue(cursor.curr().toString(), WeatherAPI.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    logger.debug("Update DB - outside time limit");
                    retrieveWeather(location);
                    mongoDBModifyer(location, query);
                }

                logger.debug("Stored Time " + storedTime);
                logger.debug("Current Time " + timeStampTime);
                logger.debug("Diff " + elapsedTime);

            } else {
                logger.debug("Not cached on the date");
                retrieveWeather(location);
                mongoDBModifyer(location, query);
            }
        } else {
            logger.debug("Not in DB!");
            retrieveWeather(location);
            mongoDBModifyer(location);

            logger.debug("Current Date: " + timeStampDate);
            logger.debug("Current Time: " + timeStampTime);
        }

        return weatherAPI;
    }

    /**
     * Connect to URL, map Json to Pojo, update the DB
     * @param location is the location for the weather
     */
    private void retrieveWeather(String location) {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID=71759c247128dfaa3e5c634da54ca3e1");

            logger.debug("The URI - " + url);

            URLConnection conn = url.openConnection();

            weatherAPI = objectMapper.readValue(conn.getInputStream(), WeatherAPI.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new record in the DB
     * @param location is used as the ID for the record
     */
    private void mongoDBModifyer(String location) {
        /*
         * Library to convert pojo to Json
         */
        Gson gson = new Gson();
        String json = gson.toJson(weatherAPI);

        logger.debug(json);

        DBObject dbObject = (DBObject) JSON.parse(json);

        dbObject.put("_id", location);
        dbObject.put("date", timeStampDate);
        dbObject.put("time", timeStampTime);

        collection.insert(dbObject);

        logger.debug("No. of entries in DB: " + Integer.toString((int) collection.count()));
    }

    /**
     * Updates the DB with a given query and data
     * @param location is used as the ID for the record
     * @param query is the search query to retrieve the correct record
     */
    private void mongoDBModifyer(String location, BasicDBObject query) {

        Gson gson = new Gson();
        String json = gson.toJson(weatherAPI);

        logger.debug(json);

        DBObject dbObject = (DBObject) JSON.parse(json);

        dbObject.put("_id", location);
        dbObject.put("date", timeStampDate);
        dbObject.put("time", timeStampTime);

        collection.update(query, dbObject);
    }
}
