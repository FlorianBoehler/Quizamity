package com.quizamity.api;

import com.quizamity.dto.UserCreateDto;
import com.quizamity.dto.UserResponseDto;
import com.quizamity.dto.UserUpdateDto;
import com.quizamity.service.UserService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "Authentication",
        description = "JWT token",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
@Tag(name = "Benutzer", description = "API zur Verwaltung von Benutzern")
public class UserResource {

    @Inject
    private UserService userService;

    @Inject
    JsonWebToken jwt;

    @POST
    @Operation(summary = "Neuen Benutzer erstellen", description = "Legt einen neuen Benutzer mit Benutzername, E-Mail, Passwort und Rolle an.")
    public Response createUser(@Valid UserCreateDto dto) {
        userService.createUser(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Benutzer abrufen", description = "Gibt den Benutzer mit der angegebenen UUID zurück.")
    public Response getUser(@PathParam("id") UUID id) {
        return userService.getUser(id) // Optional<UserResponseDto>
                .map(dto -> Response.ok(dto).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }


    @GET
    @Operation(summary = "Alle Benutzer abrufen", description = "Gibt eine Liste aller Benutzer im System zurück.")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Benutzer löschen", description = "Löscht den Benutzer mit der angegebenen ID.")
    public Response deleteUser(@PathParam("id") UUID id) {
        return userService.deleteUser(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Benutzer aktualisieren", description = "Aktualisiert die Daten eines bestehenden Benutzers.")
    public Response updateUser(@PathParam("id") UUID id, @Valid UserUpdateDto dto) {
        return userService.updateUser(id, dto)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

}
