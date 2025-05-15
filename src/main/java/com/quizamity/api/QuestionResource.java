package com.quizamity.api;

import com.quizamity.dto.QuestionCreateDto;
import com.quizamity.dto.QuestionResponseDto;
import com.quizamity.dto.QuestionUpdateDto;
import com.quizamity.service.QuestionService;
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

@Path("/questions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Fragen", description = "API zur Verwaltung von Fragen")
public class QuestionResource {

    @Inject
    private QuestionService questionService;

    @POST
    @Operation(summary = "Neue Frage erstellen", description = "Legt eine neue Frage mit Text, Schwierigkeit, Kategorie und Ersteller an.")
    public Response createQuestion(@Valid QuestionCreateDto dto) {
        questionService.createQuestion(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Frage abrufen", description = "Gibt die Frage mit der angegebenen ID zurück.")
    public Response getQuestion(
            @Parameter(description = "ID der Frage", required = true)
            @PathParam("id") UUID id) {

        return questionService.getQuestion(id)
                .map(Response::ok)
                .map(Response.ResponseBuilder::build)
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Operation(summary = "Alle Fragen abrufen", description = "Gibt eine Liste aller Fragen zurück.")
    public List<QuestionResponseDto> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Frage aktualisieren", description = "Aktualisiert die Daten einer bestehenden Frage.")
    public Response updateQuestion(
            @Parameter(description = "ID der zu aktualisierenden Frage", required = true)
            @PathParam("id") UUID id,
            @Valid QuestionUpdateDto dto) {

        return questionService.updateQuestion(id, dto)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Frage löschen", description = "Löscht die Frage mit der angegebenen ID.")
    public Response deleteQuestion(
            @Parameter(description = "ID der zu löschenden Frage", required = true)
            @PathParam("id") UUID id) {

        return questionService.deleteQuestion(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
