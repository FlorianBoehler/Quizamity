package com.quizamity.api;

import com.quizamity.service.AuthenticateService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.UUID;

@Path("/authenticate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Authentifzierung", description = "API Login Generierung von JWT Token zum Zugrif auf die API  ")
public class AuthenticateResource {

    @Inject
    private AuthenticateService authenticateService;

    @GET
    @Path("/{username}/{password}")
    @Operation(summary = "Benutzer Authentifizieren", description = "Zugangsdaten abfragen und JWT Token erstellen ")
    public String authenticate(
            @Parameter(description = "Nutzer name") @PathParam("username") String username, @Parameter(description = "password") @PathParam("password") String password)
            {
        return authenticateService.authenticate(username,password).toString();
    }
}