package fr.univtln.project.d35.server.JSF;

import fr.univtln.project.d35.server.entities.Job;
import fr.univtln.project.d35.server.entities.Profile;
import fr.univtln.project.d35.server.services.GenericBean;
import lombok.Data;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Created by soelielerch on 17/11/2017.
 */

@Data
@Stateless
@SessionScoped
@Named("profileBean")
public class ProfileBeanView {

    private String name;
    private String surname;
    private int age;


   @EJB
   GenericBean<Profile> genericBean;

    /**
     * Persist the profile from JSF and return name file to
     * redirect user
     * @param jobs
     * @return file name
     */
    public String update(List<Job> jobs){
        Profile profile=new Profile();
        profile.setSurname(surname);
        profile.setName(name);
        profile.setAge(age);
        profile.setJobs(jobs);
        genericBean.persist(profile);
        return "searchProfile";
    }

    public List<Profile> getProfiles(){
        return genericBean.findAll(Profile.class);
    }

}


