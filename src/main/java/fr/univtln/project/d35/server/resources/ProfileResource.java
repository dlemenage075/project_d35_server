package fr.univtln.project.d35.server.resources;

import fr.univtln.project.d35.server.entities.Profile;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Path("/profile")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

    @EJB
    GenericBean<Profile> genericBean;

    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET
    @Path("getIt")
    public List<Profile> getIt() {
        return genericBean.findAll(Profile.class);
    }

    @POST
    @Path("create")
    public Profile insert(Profile profile) {
        return profile;
    }

    @GET
    @Path("all")
    public List<Profile> findAll() {
        return genericBean.findAll(Profile.class);
    }

    @GET
    @Path("{id}")
    public Profile find( @PathParam("id") long id) {
        return genericBean.find(Profile.class, id);
    }

    @PUT
    @Path("{id}")
    public Profile merge(Profile profile) {
        return genericBean.merge(profile);
    }

    @DELETE
    @Path("{id}")
    public void remove(Profile profile) {
        genericBean.remove(profile);
    }

    @POST
    public void persist(Profile profile) {
        genericBean.persist(profile);
    }

}
