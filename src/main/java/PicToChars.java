import java.awt.image.BufferedImage;

public class PicToChars {
    public static final int QUALITY_MIN = 1;
    public static final int QUALITY_MAX = 100;
    private static final String CHAR_GREY_SCALE = " .:-=+*#%@";
    private static final double INTENSITY_SCALE_PART = 255.0 / CHAR_GREY_SCALE.length();

    private final int mOutputQuality;
    private final int mPixelGroupSize;
    private final BufferedImage mImage;
    private StringBuilder mOutput = null;


    PicToChars(BufferedImage image, int outputQuality) {
        mImage = image;
        mOutputQuality = outputQuality;
        mPixelGroupSize = calculatePixelGroupSize();
    }


    public void convertImageToChars() {
        int outputWidth = mImage.getWidth() /  mPixelGroupSize;
        int outputHeight = mImage.getHeight() /  mPixelGroupSize;
        mOutput = new StringBuilder(outputWidth * outputHeight + outputHeight);

        for (int rowIdx = 0; rowIdx < outputHeight; rowIdx++) {
            for (int columnIdx = 0; columnIdx < outputWidth; columnIdx++) {
                double pixelGroupIntensity = getPixelGroupIntensity(rowIdx, columnIdx);
                int intensityGroup;
                if (pixelGroupIntensity >= 255) {
                    intensityGroup = CHAR_GREY_SCALE.length() - 1;
                } else {
                    intensityGroup = (int)(pixelGroupIntensity / INTENSITY_SCALE_PART);
                }

                mOutput.append(CHAR_GREY_SCALE.charAt(intensityGroup));
            }

            mOutput.append("\n");
        }
    }


    public void printImage() {
        System.out.print(mOutput.toString());
    }


    private int calculatePixelGroupSize() {
        int base = Math.min(mImage.getWidth(), mImage.getHeight());
        int unit = (int) Math.floor(base / 100.0);
        int coefficient = (100 - mOutputQuality) + 1;
        return unit * coefficient;
    }


    private double getPixelIntensity(int R, int G, int B) {
        return (0.2126 * R + 0.7152 * G + 0.0722 * B);
    }


    private int[] getRBGValues(int pixel) {
        int[] RGB = new int[3];
        RGB[0] = (pixel >> 16) & 0xFF;
        RGB[1] = (pixel >> 8) & 0xFF;
        RGB[2] = (pixel) & 0xFF;

        return RGB;
    }


    private double getPixelGroupIntensity(int outputRow, int outputColumn) {
        int row = outputRow * mPixelGroupSize;
        int column = outputColumn * mPixelGroupSize;

        int[] pixels = new int[mPixelGroupSize * mPixelGroupSize];
        mImage.getRGB(row, column, mPixelGroupSize, mPixelGroupSize, pixels, 0, mPixelGroupSize);

        double sum = 0;
        for (int pixel : pixels) {
            int[] RGB = getRBGValues(pixel);
            sum += getPixelIntensity(RGB[0], RGB[1], RGB[2]);
        }

        return sum / (mPixelGroupSize *  mPixelGroupSize);
    }
}
