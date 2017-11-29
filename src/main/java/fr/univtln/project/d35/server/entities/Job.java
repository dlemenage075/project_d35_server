package fr.univtln.project.d35.server.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;



@Data
@Entity
@Stateless
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
@Named("job")
@SessionScoped
@XmlAccessorType(XmlAccessType.FIELD)
public class Job implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Min(value = 1, message = "Your salary need to be strictly greater than 0")
    private int salary;

    @NotNull(message = "You need to choose a job name")
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

}
