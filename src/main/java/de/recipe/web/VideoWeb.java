package de.recipe.web;

import lombok.Data;

@Data
public class VideoWeb {
    private String patch;

    public VideoWeb(String patch) {
        this.patch = patch;
    }

    public String getPatch() {
        return patch;
    }

    public VideoWeb() {
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }
}
