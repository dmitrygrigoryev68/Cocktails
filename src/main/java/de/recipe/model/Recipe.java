package de.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Transactional
@Entity
@EqualsAndHashCode
@Data
@Table( name = "recipe" )
public class Recipe implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "recipe_id" )
    private Long id;

    private String title;

    @Column( length = 3000 )
    private String announce;

    @CreationTimestamp
    @Column( nullable = false, updatable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date publicationDate;

    @OneToOne( cascade = CascadeType.ALL, targetEntity = User.class )
    @JoinColumn( name = "user_id" )
    private User author;

    @ManyToMany( cascade = CascadeType.ALL, targetEntity = Ingredient.class )
    @LazyCollection( LazyCollectionOption.FALSE )
    private List <Ingredient> ingredients;

    @OneToMany( cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )

    private List <RecepiStep> instructions;

    @OneToMany( cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )

    private List <Taxonomy> tags;

    @Column
    private int prepTimeMinute;

    @Column
    private int cookingTime;

    @ManyToMany( cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )

    private List <Photo> image;

    @OneToMany( cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )

    private List <Video> video;

    public Recipe() {
    }

    public Recipe(String title, String announce, Date publicationDate, User author, List <Ingredient> ingredients, List <RecepiStep> instructions, List <Taxonomy> tags, int prepTimeMinute, int cookingTime, List <Photo> image, List <Video> video) {
        this.title = title;
        this.announce = announce;
        this.publicationDate = publicationDate;
        this.author = author;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.tags = tags;
        this.prepTimeMinute = prepTimeMinute;
        this.cookingTime = cookingTime;
        this.image = image;
        this.video = video;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnounce() {
        return announce;
    }

    public void setAnnounce(String announce) {
        this.announce = announce;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List <Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List <Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List <RecepiStep> getInstructions() {
        return instructions;
    }

    public void setInstructions(List <RecepiStep> instructions) {
        this.instructions = instructions;
    }

    public List <Taxonomy> getTags() {
        return tags;
    }

    public void setTags(List <Taxonomy> tags) {
        this.tags = tags;
    }

    public int getPrepTimeMinute() {
        return prepTimeMinute;
    }

    public void setPrepTimeMinute(int prepTimeMinute) {
        this.prepTimeMinute = prepTimeMinute;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public List <Photo> getImage() {
        return image;
    }

    public void setImage(List <Photo> image) {
        this.image = image;
    }

    public List <Video> getVideo() {
        return video;
    }

    public void setVideo(List <Video> video) {
        this.video = video;
    }
}
