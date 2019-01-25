package de.model;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table
public class RecepiSteps  implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String stepTitle;
    @Column
    private String stepDescription;

    // private Photo photo;
    // private Video video;
    // private Audio audio;
}
