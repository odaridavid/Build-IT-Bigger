package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import notex.android.blackcoder.com.jokeproviderjava.JokeProvider;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** An endpoint method that gives a joke */
    @ApiMethod(name = "provideJoke")
    public MyBean provideJoke() {
        MyBean response = new MyBean();
        JokeProvider jokeProvider = new JokeProvider();
        response.setData(jokeProvider.jokeResponse());
        return response;
    }

}
