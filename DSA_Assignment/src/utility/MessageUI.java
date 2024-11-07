package utility;

public class MessageUI {
    
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE

    public static void success(String message) {
        System.out.println(GREEN + "SUCCESS: " + message + " " + RESET);
    }

    public static void cancelled(String message) {
        System.out.println(PURPLE + "CANCELLED: " + message + " " + RESET);
    }

    public static void failed(String message) {
        System.out.println(PURPLE + "FAILED: " + message + " " + RESET);
    }

    public static void error(String message) {
        System.out.println(PURPLE + "ERROR: " + message + " " + RESET);
    }

    public static void hint(String message) {
        System.out.println(PURPLE + "HINT: " + message + " " + RESET);
    }

    public static void otherMsg(String message, int nextLine) {
        for (int i = 0; i < nextLine; i++) {
            System.out.println();
        }
        System.out.println(BLUE + "< " + message + " >" + RESET);
    }
}
