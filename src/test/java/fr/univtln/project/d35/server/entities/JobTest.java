package fr.univtln.project.d35.server.entities;

import fr.univtln.project.d35.server.services.GenericBean;
import fr.univtln.project.d35.server.resources.JobResource;
import fr.univtln.project.d35.server.services.GenericBeanLocal;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JobTest {

    // List of 2 jobs : DEVELOPER and BAKER
    private List<Job> jobList0;

    private JobResource jobResource;

    private Job developerJob;
    private Job bakerJob;

    // Using mockito to fake a GenericBean
    @Mock
    private GenericBean genericBean;

    // Tell mockito to fake all objects annotated by @Mock
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void init() {
        jobResource = new JobResource(genericBean);

        developerJob = new Job.JobBuilder()
                .setName(Job.NAME.DEVELOPER)
                .setSalary(2500)
                .build();

        bakerJob = new Job.JobBuilder()
                .setName(Job.NAME.BAKER)
                .setSalary(3000)
                .build();

        jobList0 = new ArrayList<>();
        jobList0.add(developerJob);
        jobList0.add(bakerJob);
    }

    @Test
    public void findAll() {
        when(genericBean.findAll(Job.class)).thenReturn(jobList0);
        assertEquals("Must return a list of 2 jobs and use GenericBean",jobList0, jobResource.findAll());
        verify(genericBean).findAll(Job.class);
    }

    @Test
    public void find() {
        when(genericBean.find(Job.class, 0L)).thenReturn(developerJob);
        assertEquals("Must return a job and use GenericBean",developerJob, jobResource.find(0L));
        verify(genericBean).find(Job.class,0L);
    }

    @Test
    public void merge() {
        when(genericBean.merge(developerJob)).thenReturn(developerJob);
        assertEquals("Must return a job and use GenericBean",developerJob, jobResource.merge(developerJob));
        verify(genericBean).merge(developerJob);
    }

}
