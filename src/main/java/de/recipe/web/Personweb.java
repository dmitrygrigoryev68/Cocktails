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
}
