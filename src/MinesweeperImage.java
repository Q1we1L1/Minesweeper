import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.io.Serializable;

public class MinesweeperImage extends ImageView implements Serializable {
    public String url;

    public MinesweeperImage() {
    }

    public MinesweeperImage(String url) {

        super(url);
        this.url = url;
    }

    public MinesweeperImage(MSImage image) {
        super(image);
        if (image != null)
            this.url = image.url;

    }


}

class MSImage extends Image implements Serializable {
    public String url;

    public MSImage(String url) {
        super(url);
        this.url = url;
    }

    public MSImage(String url, boolean backgroundLoading) {
        super(url, backgroundLoading);
    }

    public MSImage(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth);
    }

    public MSImage(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth, boolean backgroundLoading) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth, backgroundLoading);
    }

    public MSImage(InputStream is) {
        super(is);
    }

    public MSImage(InputStream is, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth) {
        super(is, requestedWidth, requestedHeight, preserveRatio, smooth);
    }
}
