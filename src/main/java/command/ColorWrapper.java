package command;

public class ColorWrapper {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";

  public static String red(String string) {
    StringBuilder sb = new StringBuilder();

    sb.append(ANSI_RED);
    sb.append(string);
    sb.append(ANSI_RESET);
    return sb.toString();
  }

  public static String green(String string) {
    StringBuilder sb = new StringBuilder();

    sb.append(ANSI_GREEN);
    sb.append(string);
    sb.append(ANSI_RESET);
    return sb.toString();
  }
}
