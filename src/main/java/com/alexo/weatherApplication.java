package com.alexo;

import com.alexo.resources.RetrieveWeather;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class weatherApplication extends Application<weatherConfiguration> {

    public static void main(final String[] args) throws Exception {
        new weatherApplication().run(args);
    }

    @Override
    public String getName() {
        return "weather";
    }

    @Override
    public void initialize(final Bootstrap<weatherConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final weatherConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        final RetrieveWeather resource = new RetrieveWeather(

        );

        environment.jersey().register(resource);
    }

}
