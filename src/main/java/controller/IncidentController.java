package controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.DTO.IncidentDTO;
import service.IncidentService;
import state.Event;

import java.util.List;

@Path("/incidents")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IncidentController {

    @Inject
    IncidentService incidentService;



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createIncident(IncidentDTO incident) {

        String userId = "dev-user";


        IncidentDTO created = incidentService.createIncident(incident, userId);

        return Response.status(Response.Status.CREATED)
                .status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllIncidents() {

        List<IncidentDTO> incidents = incidentService.getAllIncidents();

        return Response
                .ok(incidents)
                .build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIncident(@PathParam("id") Long incidentId) {

        IncidentDTO incidentDTO = incidentService.getIncident(incidentId);

        return Response.ok(incidentDTO).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAssignedTo(
            @PathParam("id") Long incidentId,
            String newAssignedToUserId) {

        IncidentDTO updated = incidentService.updateAssignedTo(incidentId, newAssignedToUserId);
        return Response.ok(updated).build();
    }

    @PUT
    @Path("/{id}/state")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateState(
            @PathParam("id") Long incidentId,
            String eventName) {

        Event event = Event.valueOf(eventName);

        IncidentDTO updated =
                incidentService.updateState(incidentId, event);

        return Response.ok(updated).build();
    }






}
