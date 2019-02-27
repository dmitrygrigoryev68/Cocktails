package de.recipe.web;

import lombok.Data;

@Data
public class Personweb {

    private String nameaAuthor;

    public Personweb(String nameaAuthor) {
        this.nameaAuthor = nameaAuthor;
    }

    public Personweb() {
    }

    public String getNameaAuthor() {
        return nameaAuthor;
    }

    public void setNameaAuthor(String nameaAuthor) {
        this.nameaAuthor = nameaAuthor;
    }
}
