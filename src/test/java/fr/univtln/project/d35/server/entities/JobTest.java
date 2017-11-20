package fr.univtln.project.d35.server.entities;

import fr.univtln.project.d35.server.resources.GenericBean;
import fr.univtln.project.d35.server.resources.JobResource;
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
    List<Job> jobList0;

    JobResource jobResource;

    @Mock
    GenericBean genericBean;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void init() {
        jobResource = new JobResource(genericBean);

        Job developerJob = new Job.JobBuilder()
                .setName(Job.NAME.DEVELOPER)
                .setSalary(2500)
                .build();

        Job bakerJob = new Job.JobBuilder()
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

}
