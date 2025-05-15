package com.quizamity.api;

import com.quizamity.model.User;
import com.quizamity.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Benutzer", description = "API zur Verwaltung von Benutzern")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Operation(summary = "Neuen Benutzer erstellen", description = "Legt einen neuen Benutzer mit Benutzername, E-Mail, Passwort und Rolle an.")
    public Response createUser(User user) {
        userService.createUser(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Benutzer abrufen", description = "Gibt den Benutzer mit der angegebenen UUID zurück.")
    public Response getUser(
            @Parameter(description = "ID des Benutzers", required = true)
            @PathParam("id") UUID id) {

        return userService.getUser(id)
                .map(user -> Response.ok(user).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Operation(summary = "Alle Benutzer abrufen", description = "Gibt eine Liste aller Benutzer im System zurück.")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Benutzer aktualisieren", description = "Aktualisiert die Daten eines bestehenden Benutzers.")
    public Response updateUser(
            @Parameter(description = "ID des zu aktualisierenden Benutzers", required = true)
            @PathParam("id") UUID id,
            User user) {

        return userService.updateUser(id, user)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Benutzer löschen", description = "Löscht den Benutzer mit der angegebenen ID.")
    public Response deleteUser(
            @Parameter(description = "ID des zu löschenden Benutzers", required = true)
            @PathParam("id") UUID id) {

        return userService.deleteUser(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}