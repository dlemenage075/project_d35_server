package fr.univtln.project.d35.server.JSF;

import fr.univtln.project.d35.server.entities.Job;
import lombok.Data;

import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.*;

/**
 * Created by soelielerch on 18/11/2017.
 */
@Data
@Stateless
@SessionScoped
@Named("jobBean")
public class JobBeanView {

    /**
     * Different job available
     */
    public enum NAME {
        POLICEMAN,
        BAKER,
        DEVELOPER
    }

    private Job.NAME[] jobs;

    private int salary;

    List<Job> job2;

    public Job.NAME[]getJobs() {
        return Job.NAME.values();
    }

    /**
     * List of checked jobs
     * @return list of jobs
     */
    public List<Job> getJobsChecked(){
        List<Job> myJobs=new ArrayList<>();
        for(Job.NAME nameJob:jobs){
            Job job=new Job();
            job.setName(nameJob);
            myJobs.add(job);
        }
        return myJobs;
    }

    public String setJobsForSalary(List<Job> jobs){
        job2=jobs;
        return "salary";
    }


}
