package com.quizamity.api;

import com.quizamity.dto.AnswerCreateDto;
import com.quizamity.dto.AnswerResponseDto;
import com.quizamity.dto.AnswerUpdateDto;
import com.quizamity.service.AnswerService;
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

@Path("/answers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Antworten", description = "API zur Verwaltung von Antworten auf Fragen")
public class AnswerResource {

    @Inject
    private AnswerService answerService;

    @POST
    @Operation(summary = "Antwort erstellen", description = "Erstellt eine neue Antwort zu einer Frage.")
    public Response createAnswer(@Valid AnswerCreateDto dto) {
        answerService.createAnswer(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Antwort abrufen", description = "Liefert eine Antwort anhand ihrer ID.")
    public Response getAnswer(@PathParam("id") UUID id) {
        return answerService.getAnswer(id)
                .map(Response::ok)
                .map(Response.ResponseBuilder::build)
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Operation(summary = "Alle Antworten abrufen", description = "Liefert alle gespeicherten Antworten.")
    public List<AnswerResponseDto> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GET
    @Path("/question/{questionId}")
    @Operation(summary = "Antworten zu Frage", description = "Liefert alle Antworten zu einer bestimmten Frage.")
    public List<AnswerResponseDto> getAnswersByQuestion(
            @Parameter(description = "ID der Frage") @PathParam("questionId") UUID questionId) {

        return answerService.getAnswersByQuestion(questionId);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Antwort löschen", description = "Löscht eine Antwort anhand der ID.")
    public Response deleteAnswer(@PathParam("id") UUID id) {
        return answerService.deleteAnswer(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Antwort aktualisieren", description = "Aktualisiert den Text oder die Korrektheit einer Antwort.")
    public Response updateAnswer(
            @Parameter(description = "ID der zu aktualisierenden Antwort", required = true)
            @PathParam("id") UUID id,
            @Valid AnswerUpdateDto dto) {

        return answerService.updateAnswer(id, dto)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

}
