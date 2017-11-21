package fr.univtln.project.d35.server.entities;

import fr.univtln.project.d35.server.resources.GenericBean;
import fr.univtln.project.d35.server.resources.ProfileResource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.MockitoJUnit;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ProfileTest {

    // List of 2 profiles : profile0 and profile1
    private List<Profile> profileList0;

    // Profile with 2 jobs
    private Profile profile0;

    // Profile without jobs
    private Profile profile1;

    //JobResource jobResource;
    private ProfileResource profileResource;

    // Using mockito to fake a GenericBean
    @Mock
    private GenericBean<Profile> genericBean;

    // Tell mockito to fake all objects annotated by @Mock
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void init() {

        profileResource = new ProfileResource(genericBean);

        Job developerJob = new Job.JobBuilder()
                .setName(Job.NAME.DEVELOPER)
                .setSalary(2500)
                .build();

        Job bakerJob = new Job.JobBuilder()
                .setName(Job.NAME.BAKER)
                .setSalary(3000)
                .build();

        // List of 2 jobs : DEVELOPER and BAKER
        List<Job> jobList0 = new ArrayList<>();
        jobList0.add(developerJob);
        jobList0.add(bakerJob);

        // Initialise Profiles
        profile0 = new Profile.ProfileBuilder()
                .setAge(22)
                .setName("Damien")
                .setSurname("LEMÉNAGER")
                .setJobs(jobList0)
                .build();

        profile1 = new Profile.ProfileBuilder()
                .setAge(30)
                .setName("Pierrot")
                .setSurname("DELEVEGA")
                .build();

        profileList0 = new ArrayList<>();
        profileList0.add(profile0);
        profileList0.add(profile1);
    }

    @Test
    public void getTaxes() {
        assertEquals("Profile with different jobs must have taxes > 0",550,profile0.getTaxes());
        assertEquals("Profile without jobs must have 0 taxes",0,profile1.getTaxes());
    }

    @Test
    public void findAll() {
        when(genericBean.findAll(Profile.class)).thenReturn(profileList0);
        assertEquals("Must return a list of 2 profiles and use GenericBean",profileList0, profileResource.findAll());
        verify(genericBean).findAll(Profile.class);
    }

    @Test
    public void find() {
        when(genericBean.find(Profile.class, 0L)).thenReturn(profile0);
        assertEquals("Must return a profile and use GenericBean",profile0, profileResource.find(0L));
        verify(genericBean).find(Profile.class,0L);
    }

    @Test
    public void merge() {
        when(genericBean.merge(profile1)).thenReturn(profile1);
        assertEquals("Must return a profile and use GenericBean",profile1, profileResource.merge(profile1));
        verify(genericBean).merge(profile1);
    }

}
