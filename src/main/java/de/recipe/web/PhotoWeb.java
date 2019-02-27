package de.recipe.web;

import lombok.Data;

@Data
public class PhotoWeb {
    private String patch;

    public PhotoWeb(String patch) {
        this.patch = patch;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public PhotoWeb() {
    }
}
