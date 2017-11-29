package fr.univtln.project.d35.server.resources;

import fr.univtln.project.d35.server.entities.Profile;
import fr.univtln.project.d35.server.services.GenericBean;
import lombok.NoArgsConstructor;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/profiles")
@Stateless
@NoArgsConstructor
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

    @EJB
    GenericBean<Profile> genericBean;

    // Only for Unit test with Mockito
    public ProfileResource(GenericBean genericBean) {
        this.genericBean = genericBean;
    }

    @GET
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
    public void remove( @PathParam("id") long id) {
        Profile profile = genericBean.find(Profile.class, id);
        genericBean.remove(profile);
    }

    @POST
    public Profile persist(Profile profile) {
        return genericBean.persist(profile);
    }

}
