package de.recipe.web;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RecipeWebOutput {
    private Long id;
    private String title;
    private String announce;
    private Date publicationDate;
    private Userweb author;
    private List <IngredientWeb> ingredients;
    private List <RecepiStepWeb> instructions;
    private List <TaxonomyWeb> tags;
    private int prepTimeMinute;
    private int cookingTime;
    private List <PhotoWeb> image;
    private List <VideoWeb> video;

    public RecipeWebOutput() {
    }

    public RecipeWebOutput(Long id, String title, String announce,  Userweb author, List <IngredientWeb> ingredients, List <RecepiStepWeb> instructions, List <TaxonomyWeb> tags, int prepTimeMinute, int cookingTime, List <PhotoWeb> image, List <VideoWeb> video) {
        this.id = id;
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

    public Userweb getAuthor() {
        return author;
    }

    public void setAuthor(Userweb author) {
        this.author = author;
    }

    public List <IngredientWeb> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List <IngredientWeb> ingredients) {
        this.ingredients = ingredients;
    }

    public List <RecepiStepWeb> getInstructions() {
        return instructions;
    }

    public void setInstructions(List <RecepiStepWeb> instructions) {
        this.instructions = instructions;
    }

    public List <TaxonomyWeb> getTags() {
        return tags;
    }

    public void setTags(List <TaxonomyWeb> tags) {
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

    public List <PhotoWeb> getImage() {
        return image;
    }

    public void setImage(List <PhotoWeb> image) {
        this.image = image;
    }

    public List <VideoWeb> getVideo() {
        return video;
    }

    public void setVideo(List <VideoWeb> video) {
        this.video = video;
    }
}
