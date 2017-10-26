package fr.univtln.project.d35.server.resources;


import fr.univtln.project.d35.server.entities.Job;
import fr.univtln.project.d35.server.exception.ResourceException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/job")
public class JobResource extends Resource implements Resoursable<Job>{

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") long l) throws ResourceException {
        return super.get(Job.class, l);
    }

    @GET
    public Response fetch() {
        return super.fetch(Job.class);
    }

    @POST
    public Response insert(Job job) {
        return super.create(job);
    }

    @PUT
    @Path("{id}")
    public Response merge(Job job,@PathParam("id") long l) throws ResourceException {
        return super.update(job, l);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long l) throws ResourceException {
        return super.delete(Job.class, l);
    }
}
