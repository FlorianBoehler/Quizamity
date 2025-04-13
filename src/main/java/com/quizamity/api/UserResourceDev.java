package com.quizamity.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/userstest")
public class UserResourceDev {

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from REST!";
    }
}