package com.quizamity.api;

import com.quizamity.model.GameSession;
import com.quizamity.service.GameSessionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/game-sessions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Spiel-Sessions", description = "API zur Verwaltung von GameSessions (Spielverläufen)")
public class GameSessionResource {

    @Inject
    private GameSessionService gameSessionService;

    @POST
    @Operation(summary = "GameSession erstellen", description = "Erstellt einen neuen Spielverlauf.")
    public Response createGameSession(GameSession gameSession) {
        gameSessionService.createGameSession(gameSession);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "GameSession abrufen", description = "Gibt eine GameSession anhand der ID zurück.")
    public Response getGameSession(
            @Parameter(description = "ID der GameSession") @PathParam("id") UUID id) {

        return gameSessionService.getGameSession(id)
                .map(gs -> Response.ok(gs).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Operation(summary = "Alle GameSessions abrufen", description = "Liefert alle gespeicherten Spielverläufe.")
    public List<GameSession> getAllGameSessions() {
        return gameSessionService.getAllGameSessions();
    }

    @GET
    @Path("/game/{gameId}")
    @Operation(summary = "Sessions zu einem Spiel", description = "Liefert alle Sessions zu einem bestimmten Spiel.")
    public List<GameSession> getSessionsByGame(@PathParam("gameId") UUID gameId) {
        return gameSessionService.getGameSessionsByGame(gameId);
    }

    @GET
    @Path("/user/{userId}")
    @Operation(summary = "Sessions eines Nutzers", description = "Liefert alle Sessions eines bestimmten Nutzers.")
    public List<GameSession> getSessionsByUser(@PathParam("userId") UUID userId) {
        return gameSessionService.getGameSessionsByUser(userId);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "GameSession aktualisieren")
    public Response updateGameSession(@PathParam("id") UUID id, GameSession updated) {
        return gameSessionService.updateGameSession(id, updated)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "GameSession löschen")
    public Response deleteGameSession(@PathParam("id") UUID id) {
        return gameSessionService.deleteGameSession(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
