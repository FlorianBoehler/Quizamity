package com.quizamity.api;

import com.quizamity.dto.GameSessionResponseDto;
import com.quizamity.dto.GameSessionUpdateDto;
import com.quizamity.dto.GameSessionCreateDto;
import com.quizamity.model.GameSession;
import com.quizamity.service.GameSessionService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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
    @Operation(summary = "GameSession erstellen")
    public Response createGameSession(@Valid GameSessionCreateDto dto) {
        gameSessionService.createGameSession(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "GameSession abrufen")
    public Response getGameSession(@PathParam("id") UUID id) {
        return gameSessionService.getGameSession(id)
                .map(Response::ok)
                .map(Response.ResponseBuilder::build)
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Operation(summary = "Alle GameSessions abrufen")
    public List<GameSessionResponseDto> getAllGameSessions() {
        return gameSessionService.getAllGameSessions();
    }

    @GET
    @Path("/game/{gameId}")
    @Operation(summary = "Sessions eines Spiels abrufen")
    public List<GameSessionResponseDto> getSessionsByGame(@PathParam("gameId") UUID gameId) {
        return gameSessionService.getSessionsByGame(gameId);
    }

    @GET
    @Path("/user/{userId}")
    @Operation(summary = "Sessions eines Nutzers abrufen")
    public List<GameSessionResponseDto> getSessionsByUser(@PathParam("userId") UUID userId) {
        return gameSessionService.getSessionsByUser(userId);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "GameSession aktualisieren")
    public Response updateGameSession(@PathParam("id") UUID id, @Valid GameSessionUpdateDto dto) {
        return gameSessionService.updateGameSession(id, dto)
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
