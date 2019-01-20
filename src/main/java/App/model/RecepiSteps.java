package App.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class RecepiSteps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stepID;
    private String stepTitle;
    private String stepDescription;

   // private Photo photo;
    // private Video video;
    // private Audio audio;
}
