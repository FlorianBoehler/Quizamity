package com.quizamity.api;

import com.quizamity.model.Category;
import com.quizamity.service.CategoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Kategorien", description = "API zur Verwaltung von Fragethemen/Kategorien")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @POST
    @Operation(summary = "Neue Kategorie erstellen", description = "Legt eine neue Kategorie an.")
    public Response createCategory(Category category) {
        categoryService.createCategory(category);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Kategorie abrufen", description = "Gibt eine Kategorie anhand ihrer ID zurück.")
    public Response getCategory(
            @Parameter(description = "ID der Kategorie", required = true)
            @PathParam("id") UUID id) {

        return categoryService.getCategory(id)
                .map(cat -> Response.ok(cat).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Operation(summary = "Alle Kategorien abrufen", description = "Gibt alle verfügbaren Kategorien zurück.")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Kategorie aktualisieren", description = "Aktualisiert den Namen einer bestehenden Kategorie.")
    public Response updateCategory(
            @Parameter(description = "ID der zu aktualisierenden Kategorie", required = true)
            @PathParam("id") UUID id,
            Category category) {

        return categoryService.updateCategory(id, category)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Kategorie löschen", description = "Löscht die Kategorie mit der angegebenen ID.")
    public Response deleteCategory(
            @Parameter(description = "ID der zu löschenden Kategorie", required = true)
            @PathParam("id") UUID id) {

        return categoryService.deleteCategory(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
