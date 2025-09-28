public class Main {
    public static void main(String[] args) {
        int[] output_quality = {0};
        String[] file = {""};
        if (validateArgs(args, file, output_quality)) {
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
            System.err.println("output-quality must be an integer");
            return false;
        }

        return true;
    }
}
