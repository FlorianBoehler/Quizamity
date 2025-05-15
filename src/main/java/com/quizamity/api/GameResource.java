package com.quizamity.api;

import com.quizamity.model.Game;
import com.quizamity.service.GameService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
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
    @Operation(summary = "Spiel erstellen", description = "Erstellt ein neues Spiel mit Kategorie und Spielmodus.")
    public Response createGame(Game game) {
        gameService.createGame(game);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Spiel abrufen", description = "Gibt ein Spiel anhand der ID zurück.")
    public Response getGame(
            @Parameter(description = "ID des Spiels") @PathParam("id") UUID id) {

        return gameService.getGame(id)
                .map(g -> Response.ok(g).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Operation(summary = "Alle Spiele abrufen", description = "Liefert eine Liste aller gespeicherten Spiele.")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Spiel aktualisieren")
    public Response updateGame(@PathParam("id") UUID id, Game game) {
        return gameService.updateGame(id, game)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Spiel löschen")
    public Response deleteGame(@PathParam("id") UUID id) {
        return gameService.deleteGame(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
