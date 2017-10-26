package fr.univtln.project.d35.server.resources;

import fr.univtln.project.d35.server.entities.Profile;
import fr.univtln.project.d35.server.exception.ResourceException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/profile")
public class ProfileResource extends Resource implements Resoursable<Profile>{

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") long l) throws ResourceException {
        return super.get(Profile.class, l);
    }

    @GET
    public Response fetch() {
        return super.fetch(Profile.class);
    }

    @Override
    public Response insert(Profile profile) {
        return super.create(profile);
    }

    @PUT
    @Path("{id}")
    public Response merge(Profile profile,@PathParam("id") long l) throws ResourceException {
        return super.update(profile, l);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long l) throws ResourceException {
        return super.delete(Profile.class, l);
    }


}
