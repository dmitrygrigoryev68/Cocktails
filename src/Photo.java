import javafx.scene.image.Image;

import java.io.InputStream;

public class Photo extends Image {

    private String alt;
    private String title;
    private String filename;




    public Photo(String url) {
        super(url);
    }

    public Photo(String url, boolean backgroundLoading) {
        super(url, backgroundLoading);
    }

    public Photo(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth);
    }

    public Photo(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth, boolean backgroundLoading) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth, backgroundLoading);
    }

    public Photo(InputStream is) {
        super(is);
    }

    public Photo(InputStream is, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth) {
        super(is, requestedWidth, requestedHeight, preserveRatio, smooth);
    }
}
