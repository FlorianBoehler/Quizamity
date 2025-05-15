package com.quizamity.api;

import com.quizamity.model.Answer;
import com.quizamity.service.AnswerService;
import jakarta.inject.Inject;
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
    public Response createAnswer(Answer answer) {
        answerService.createAnswer(answer);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Antwort abrufen", description = "Liefert eine Antwort anhand ihrer ID.")
    public Response getAnswer(
            @Parameter(description = "ID der Antwort") @PathParam("id") UUID id) {

        return answerService.getAnswer(id)
                .map(a -> Response.ok(a).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Operation(summary = "Alle Antworten abrufen", description = "Liefert alle gespeicherten Antworten.")
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GET
    @Path("/question/{questionId}")
    @Operation(summary = "Antworten zu Frage", description = "Liefert alle Antworten zu einer bestimmten Frage.")
    public List<Answer> getAnswersByQuestion(
            @Parameter(description = "ID der Frage") @PathParam("questionId") UUID questionId) {

        return answerService.getAnswersByQuestion(questionId);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Antwort aktualisieren")
    public Response updateAnswer(
            @PathParam("id") UUID id,
            Answer answer) {

        return answerService.updateAnswer(id, answer)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Antwort löschen")
    public Response deleteAnswer(
            @PathParam("id") UUID id) {

        return answerService.deleteAnswer(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
