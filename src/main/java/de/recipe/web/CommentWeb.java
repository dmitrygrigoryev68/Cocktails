package de.recipe.web;

import lombok.Data;

@Data
public class CommentWeb {
    private String comment;

    public CommentWeb(String comment) {
        this.comment = comment;
    }

    public CommentWeb() {
    }
}
