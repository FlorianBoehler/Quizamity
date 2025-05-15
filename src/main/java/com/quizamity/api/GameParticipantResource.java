package com.quizamity.api;

import com.quizamity.dto.GameParticipantCreateDto;
import com.quizamity.dto.GameParticipantResponseDto;
import com.quizamity.dto.GameParticipantUpdateDto;
import com.quizamity.model.GameParticipant;
import com.quizamity.service.GameParticipantService;
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

@Path("/game-participants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Spielteilnehmer", description = "API zur Verwaltung der GameParticipants (Spielteilnehmer)")
public class GameParticipantResource {

    @Inject
    private GameParticipantService gameParticipantService;

    @POST
    @Operation(summary = "Teilnehmer erstellen")
    public Response createParticipant(@Valid GameParticipantCreateDto dto) {
        gameParticipantService.createParticipant(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Teilnehmer abrufen")
    public Response getParticipant(@PathParam("id") UUID id) {
        return gameParticipantService.getById(id)
                .map(Response::ok)
                .map(Response.ResponseBuilder::build)
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Operation(summary = "Alle Teilnehmer")
    public List<GameParticipantResponseDto> getAllParticipants() {
        return gameParticipantService.getAll();
    }

    @GET
    @Path("/game/{gameId}")
    @Operation(summary = "Teilnehmer eines Spiels")
    public List<GameParticipantResponseDto> getByGame(@PathParam("gameId") UUID gameId) {
        return gameParticipantService.getByGame(gameId);
    }

    @GET
    @Path("/user/{userId}")
    @Operation(summary = "Teilnahmen eines Nutzers")
    public List<GameParticipantResponseDto> getByUser(@PathParam("userId") UUID userId) {
        return gameParticipantService.getByUser(userId);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Teilnehmer aktualisieren")
    public Response updateParticipant(@PathParam("id") UUID id, @Valid GameParticipantUpdateDto dto) {
        return gameParticipantService.update(id, dto)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Teilnehmer löschen")
    public Response deleteParticipant(@PathParam("id") UUID id) {
        return gameParticipantService.delete(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

}
