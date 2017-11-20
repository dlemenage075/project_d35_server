package fr.univtln.project.d35.server.entities;


import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data @Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Profile implements Serializable {

    @Id
    @GeneratedValue
    @XmlElement
    private int id;

    @NotNull(message = "Name is empty") @Size(max = 50, message = "Name cannot be greater than 50 characters")
    @XmlElement
    private String name;
    @NotNull(message = "Surname is empty") @Size(max = 70, message = "Surname cannot be greater than 70 characters")
    @XmlElement
    private String surname;

    @OneToMany(cascade = CascadeType.PERSIST)
    @XmlElement
    private List<Job> jobs = new ArrayList<>();

    @Min(value = 0, message = "Your age need to be a positif number")
    @XmlElement
    private int age;

    public Profile() {
    }

    public Profile(ProfileBuilder profileBuilder) {
        this.age = profileBuilder.age;
        this.name = profileBuilder.name;
        this.surname = profileBuilder.surname;
        this.jobs = profileBuilder.jobs;
    }

    public static class ProfileBuilder {
        private String name;
        private String surname;
        private int age;
        private List<Job> jobs = new ArrayList<>();

        public ProfileBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProfileBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public ProfileBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public ProfileBuilder setJobs(List<Job> jobs) {
            this.jobs = jobs;
            return this;
        }

        public Profile build() {
            return new Profile(this);
        }
    }

    public int getTaxes() {
        int countTaxes = 0;
        for (Job job :
             jobs) {
            countTaxes += job.getSalary()/10;
        }
        return countTaxes;
    }

}
