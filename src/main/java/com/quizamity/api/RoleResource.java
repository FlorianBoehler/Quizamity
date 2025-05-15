package com.quizamity.api;

import com.quizamity.model.Role;
import com.quizamity.service.RoleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Rollen", description = "API zur Verwaltung von Benutzerrollen")
public class RoleResource {

    @Inject
    private RoleService roleService;

    @POST
    @Operation(summary = "Neue Rolle erstellen", description = "Fügt eine neue Rolle wie STUDENT oder MODERATOR hinzu.")
    public Response createRole(Role role) {
        roleService.createRole(role);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Rolle abrufen", description = "Gibt die Rolle mit der angegebenen ID zurück.")
    public Response getRole(
            @Parameter(description = "ID der Rolle") @PathParam("id") UUID id) {

        return roleService.getRole(id)
                .map(r -> Response.ok(r).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Operation(summary = "Alle Rollen abrufen", description = "Listet alle verfügbaren Rollen auf.")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
}
