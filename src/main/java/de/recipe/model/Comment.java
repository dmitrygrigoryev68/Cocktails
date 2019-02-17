package de.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;

@Transactional
@Entity
@Data
@Table
@EqualsAndHashCode
public class Comment implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(length = 1500,nullable = false)
    private String comment;
  /*  @ManyToOne( fetch = FetchType.EAGER, targetEntity = User.class, cascade = CascadeType.ALL )
     private User user;
*/
    public Comment() {
    }
//facebook realization
}
