package App.model;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table
public class Taxonomy  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nameTaxonomy;
    //private Recipe recipe;


}
