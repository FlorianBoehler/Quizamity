package com.quizamity.api;

import com.quizamity.model.GameParticipant;
import com.quizamity.service.GameParticipantService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/game-participants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Spielteilnehmer", description = "API zur Verwaltung der GameParticipants (Spielteilnehmer)")
public class GameParticipantResource {

    @Inject
    private GameParticipantService service;

    @POST
    @Operation(summary = "Teilnehmer erstellen", description = "Registriert einen Teilnehmer für ein Spiel.")
    public Response createParticipant(GameParticipant participant) {
        service.create(participant);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Teilnehmer abrufen", description = "Liefert einen Spielteilnehmer anhand der ID.")
    public Response getParticipant(@PathParam("id") UUID id) {
        return service.getById(id)
                .map(p -> Response.ok(p).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Operation(summary = "Alle Teilnehmer", description = "Listet alle GameParticipants.")
    public List<GameParticipant> getAllParticipants() {
        return service.getAll();
    }

    @GET
    @Path("/game/{gameId}")
    @Operation(summary = "Teilnehmer eines Spiels", description = "Listet Teilnehmer eines bestimmten Spiels.")
    public List<GameParticipant> getByGame(@PathParam("gameId") UUID gameId) {
        return service.getByGame(gameId);
    }

    @GET
    @Path("/user/{userId}")
    @Operation(summary = "Teilnahmen eines Nutzers", description = "Listet alle Spielteilnahmen eines bestimmten Nutzers.")
    public List<GameParticipant> getByUser(@PathParam("userId") UUID userId) {
        return service.getByUser(userId);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Teilnehmer aktualisieren")
    public Response updateParticipant(@PathParam("id") UUID id, GameParticipant participant) {
        return service.update(id, participant)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Teilnehmer löschen")
    public Response deleteParticipant(@PathParam("id") UUID id) {
        return service.delete(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
