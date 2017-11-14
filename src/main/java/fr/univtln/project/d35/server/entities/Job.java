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

    @Min(value = 0, message = "Your salary need to be a positif number")
    private int salary;

    @NotNull(message = "You need to choose a job name")
    private NAME name;
    public enum NAME {
        POLICEMAN,
        BAKER,
        DEVELOPER
    }

}
