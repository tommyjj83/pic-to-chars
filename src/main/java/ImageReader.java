import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ImageReader {
    public static BufferedImage loadImage(Path path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return image;
    }
}
