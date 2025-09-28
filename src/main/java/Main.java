public class Main {
    public static void main(String[] args) {
        if (validateArgs(args)) {
            return;
        }
    }


    private static boolean validateArgs(String[] args) {
        if (args.length != 1) {
            System.err.println("Wrong number of arguments. Usage: app path-to-image");
            return false;
        }

        return true;
    }
}
