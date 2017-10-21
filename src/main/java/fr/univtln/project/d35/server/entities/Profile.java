package fr.univtln.project.d35.server.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data @Entity
public class Profile implements Serializable{

    @Id @GeneratedValue
    private int id;

    private String name;
    private String surname;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Job> jobs = new ArrayList<>();
    private int age;

}
