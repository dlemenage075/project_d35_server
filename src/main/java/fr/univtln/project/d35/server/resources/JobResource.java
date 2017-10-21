package fr.univtln.project.d35.server.resources;


import fr.univtln.project.d35.server.entities.Job;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/job")
public class JobResource {



    @GET
    @Path("{id}")
    public Response get(@PathParam("id") long l) {
        return null;
    }

    @GET
    public Response fetch() {
        return null;
    }

    @POST
    public Response insert(Job job) {
        return null;
    }

    @PUT
    @Path("{id}")
    public Response merge(Job job,@PathParam("id") long l) {
        return null;
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long l) {
        return null;
    }
}
