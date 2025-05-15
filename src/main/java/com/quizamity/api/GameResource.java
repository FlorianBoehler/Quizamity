package com.quizamity.api;

import com.quizamity.dto.GameCreateDto;
import com.quizamity.dto.GameResponseDto;
import com.quizamity.dto.GameUpdateDto;
import com.quizamity.service.GameService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Spiele", description = "API zur Verwaltung von Quiz-Spielen")
public class GameResource {

    @Inject
    private GameService gameService;

    @POST
    @Operation(summary = "Spiel erstellen", description = "Legt ein neues Spiel mit Modus und Kategorie an.")
    public Response createGame(@Valid GameCreateDto dto) {
        gameService.createGame(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Spiel abrufen")
    public Response getGame(@PathParam("id") UUID id) {
        return gameService.getGame(id)
                .map(Response::ok)
                .map(Response.ResponseBuilder::build)
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Operation(summary = "Alle Spiele abrufen")
    public List<GameResponseDto> getAllGames() {
        return gameService.getAllGames();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Spiel löschen")
    public Response deleteGame(@PathParam("id") UUID id) {
        return gameService.deleteGame(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Spiel aktualisieren", description = "Aktualisiert ein bestehendes Spiel.")
    public Response updateGame(@PathParam("id") UUID id, @Valid GameUpdateDto dto) {
        return gameService.updateGame(id, dto)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

}
