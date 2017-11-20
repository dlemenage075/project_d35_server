package fr.univtln.project.d35.server.resources;

import fr.univtln.project.d35.server.entities.Job;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.annotation.Annotation;
import java.util.List;

@Path("/job")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JobResource {

    @EJB
    GenericBean<Job> genericBean;

    public JobResource() {
    }

    // Only for Unit test with Mockito
    public JobResource(GenericBean genericBean) {
        this.genericBean = genericBean;
    }

    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET
    @Path("getIt")
    @Produces("text/plain")
    public String getIt() {

        String str = " ok : ";
        for (Annotation annotation :
                Job.class.getAnnotations() ){

            str+= annotation.toString();

        }
        return str;
    }

    @GET
    public List<Job> findAll() {
        return genericBean.findAll(Job.class);
    }

    @GET
    @Path("{id}")
    public Job find( @PathParam("id") long id) {
        return genericBean.find(Job.class, id);
    }

    @PUT
    @Path("{id}")
    public Job merge(Job job) {
        return genericBean.merge(job);
    }

    @DELETE
    @Path("{id}")
    public void remove(Job job) {
        genericBean.remove(job);
    }

    @POST
    public void persist(Job job) {
        genericBean.persist(job);
    }

}
