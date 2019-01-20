package App.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
public class Taxonomy {
    @Id
    private Long id;
    private String name;
    //private Recipe recipe;


}
