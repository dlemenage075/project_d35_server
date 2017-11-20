package fr.univtln.project.d35.server.entities;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data @Entity
public class Job implements Serializable {

    @Id @GeneratedValue
    private int id;

    @Min(value = 1, message = "Your salary need to be strictly greater than 0")
    private int salary;

    @NotNull(message = "You need to choose a job name")
    private NAME name;

    public enum NAME {
        POLICEMAN,
        BAKER,
        DEVELOPER
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
