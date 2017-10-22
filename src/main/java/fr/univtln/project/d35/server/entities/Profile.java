package fr.univtln.project.d35.server.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data @Entity
public class Profile implements Serializable{

    @Id @GeneratedValue
    private int id;

    @NotNull(message = "Name is empty") @Size(max = 50, message = "Name cannot be greater than 50 characters")
    private String name;
    @NotNull(message = "Surname is empty") @Size(max = 70, message = "Surname cannot be greater than 70 characters")
    private String surname;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Job> jobs = new ArrayList<>();

    @Min(value = 0, message = "Your age need to be a positif number")
    private int age;

    public int getTaxes() {
        int countTaxes = 0;
        for (Job job :
             jobs) {
            countTaxes += job.getSalary()/10;
        }
        return countTaxes;
    }

}
