import java.awt.image.BufferedImage;

public class PicToChars {
    public static final int QUALITY_MIN = 1;
    public static final int QUALITY_MAX = 100;
    private final String CHAR_GREY_SCALE = " .:-=+*#%@";

    private final int mOutputQuality;
    private final BufferedImage mImage;
    private StringBuilder mOutput = null;

    PicToChars(BufferedImage image, int outputQuality) {
        mImage = image;
        mOutputQuality = outputQuality;
    }

    public void convertImageToChars() {
        int aggregatedPixels = getNumberOfAggregatedPixels();
        int outputWidth = mImage.getWidth() /  aggregatedPixels;
        int outputHeight = mImage.getHeight() /  aggregatedPixels;
        mOutput = new StringBuilder(outputWidth * outputHeight + outputHeight);

        for (int rowIdx = 0; rowIdx < outputHeight; rowIdx++) {
            for (int columnIdx = 0; columnIdx < outputWidth; columnIdx++) {

            }
        }
    }


    private int getNumberOfAggregatedPixels() {
        int base = Math.min(mImage.getWidth(), mImage.getHeight());
        int unit = (int) Math.floor(base / 100.0);
        int coefficient = (100 - mOutputQuality) + 1;
        return unit * coefficient;
    }


    private double getPixelIntensity(int R, int G, int B) {
        return (0.2126 * R + 0.7152 * G + 0.0722 * B);
    }
}
