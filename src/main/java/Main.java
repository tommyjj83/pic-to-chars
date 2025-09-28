import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        int[] output_quality = {0};
        String[] file = {""};
        if (validateArgs(args, file, output_quality)) {
            return;
        }

        try {
            BufferedImage image = ImageReader.loadImage(file[0]);
            PicToChars converter = new PicToChars(image, output_quality[0]);
            converter.convertImageToChars();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return;
        }
    }


    private static boolean validateArgs(String[] args, String[] file, int[] outputQuality) {
        if (args.length != 2) {
            System.err.println("Wrong number of arguments. Usage: app path-to-image output_quality");
            return false;
        }

        file[0] = args[0];

        try {
             outputQuality[0] = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("output-quality must be an integer from range <" + PicToChars.QUALITY_MIN +
                    ", " + PicToChars.QUALITY_MAX + ">");
            return false;
        }

        if (outputQuality[0] < PicToChars.QUALITY_MIN || outputQuality[0] > PicToChars.QUALITY_MAX) {
            System.err.println("output-quality must be from range <" + PicToChars.QUALITY_MIN +
                    ", " + PicToChars.QUALITY_MAX + ">");
            return false;
        }

        return true;
    }
}
