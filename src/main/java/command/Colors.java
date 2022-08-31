package command;

public class Colors {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static String red(String string) {
        return ANSI_RED + string + ANSI_RESET;
    }

    public static String green(String string) {
        return ANSI_GREEN + string + ANSI_RESET;
    }
}
