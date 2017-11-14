package fr.univtln.project.d35.server.resources;

import fr.univtln.project.d35.server.entities.Profile;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/profile")
@Stateless
public class ProfileResource {



    @EJB
    GenericBean<Profile> genericBean;

    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET
    @Path("getIt")
    @Produces({"application/json"})
    public List<Profile> getIt() {
        /*String str = "ok profile : ";

        for (Profile profile :
                genericBean.findAll(Profile.class)) {
            str+=profile.toString();
        }*/

        return genericBean.findAll(Profile.class);
        //return str;
        //return Response.status(Response.Status.OK).entity(genericBean.findAll(Profile.class)).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Produces("text/plain")
    public String insert() {
        Profile profile = new Profile();
        profile.setName("Damien");
        profile.setSurname("LEMÉNAGER");
        profile.setAge(22);
        genericBean.persist(profile);
        return "ID : "+Long.toString(profile.getId());
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
