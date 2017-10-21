package fr.univtln.project.d35.server.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data @Entity
public class Job implements Serializable {

    @Id @GeneratedValue
    private int id;

    private int salaire;
    private NAME name;
    public enum NAME {
        POLICEMAN,
        BAKER,
        DEVELOPER
    }

}
