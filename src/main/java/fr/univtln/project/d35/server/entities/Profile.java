package fr.univtln.project.d35.server.entities;


import lombok.*;

import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
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



@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Stateless
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Named("profile")
@Data
@SessionScoped
public class Profile implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @NotNull(message = "Name is empty") @Size(max = 50, message = "Name cannot be greater than 50 characters")
    private String name;

    @Getter
    @NotNull(message = "Surname is empty") @Size(max = 70, message = "Surname cannot be greater than 70 characters")
    private String surname;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Builder.Default // Use to set default value of this field for @Builder
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
