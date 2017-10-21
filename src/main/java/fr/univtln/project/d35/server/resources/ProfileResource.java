package fr.univtln.project.d35.server.resources;

import fr.univtln.project.d35.server.entities.Profile;
import fr.univtln.project.d35.server.resource.Resource;
import fr.univtln.project.d35.server.resource.Resoursable;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/profile")
public class ProfileResource extends Resource implements Resoursable<Profile>{

    @GET
    @Path("go")
    @Produces("text/plain")
    public String gogogo() {
        System.out.println("youhouuuuuuu");
        return "youhouuu";
    }

    @GET
    @Path("all")
    public Response getProfile() {
        return super.fetch(Profile.class);
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") long l) {
        return null;
    }

    @GET
    public Response fetch() {
        return null;
    }

    @Override
    public Response insert(Profile profile) {
        return super.create(profile);
    }

    @PUT
    @Path("{id}")
    public Response merge(Profile profile,@PathParam("id") long l) {
        return null;
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long l) {
        return null;
    }


}
