package com.quizamity.api;

import com.quizamity.dto.UserCreateDTO;
import com.quizamity.dto.UserResponseDTO;
import com.quizamity.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    public Response createUser(UserCreateDTO userDTO) {
        try {
            UserResponseDTO createdUser = userService.createUser(userDTO);
            return Response.status(Response.Status.CREATED).entity(createdUser).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from REST!";
    }

}