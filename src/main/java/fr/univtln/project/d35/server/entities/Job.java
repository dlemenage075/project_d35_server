package fr.univtln.project.d35.server.entities;

import lombok.Data;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Arrays;



@Data @Entity @Stateless
@XmlRootElement
@Named("job")
@SessionScoped
@XmlAccessorType(XmlAccessType.FIELD)
public class Job implements Serializable {

    @Id
    @GeneratedValue
    @XmlElement
    private int id;

    @Min(value = 1, message = "Your salary need to be strictly greater than 0")
    @XmlElement
    private int salary;

    @NotNull(message = "You need to choose a job name")
    @XmlElement
    private NAME name;

    public enum NAME {
        POLICEMAN,
        BAKER,
        DEVELOPER
    }


    @Override
    public String toString() {
        return name.toString();
    }

    public Job() {
    }

    public Job(JobBuilder jobBuilder) {
        this.name = jobBuilder.name;
        this.salary = jobBuilder.salary;
    }

    public static class JobBuilder {
        private int salary;
        private NAME name;

        public JobBuilder setSalary(int salary) {
            this.salary = salary;
            return this;
        }

        public JobBuilder setName(NAME name) {
            this.name = name;
            return this;
        }

        public Job build() {
            return new Job(this);
        }
    }
}
