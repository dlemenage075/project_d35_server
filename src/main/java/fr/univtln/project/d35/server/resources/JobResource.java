package fr.univtln.project.d35.server.resources;

import fr.univtln.project.d35.server.entities.Job;
import fr.univtln.project.d35.server.services.GenericBean;
import fr.univtln.project.d35.server.services.GenericBeanLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/jobs")
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
    public void remove( @PathParam("id") long id) {
        Job job = genericBean.find(Job.class, id);
        genericBean.remove(job);
    }

    @POST
    public void persist(Job job) {
        genericBean.persist(job);
    }

}
