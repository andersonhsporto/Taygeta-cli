package command;

public class ColorWrapper {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_CYAN = "\u001B[36m";

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

  public static String cyan(String string) {
    StringBuilder sb = new StringBuilder();

    sb.append(ANSI_CYAN);
    sb.append(string);
    sb.append(ANSI_RESET);
    return sb.toString();
  }

  public static String blue(String string) {
    StringBuilder sb = new StringBuilder();

    sb.append(ANSI_BLUE);
    sb.append(string);
    sb.append(ANSI_RESET);
    return sb.toString();
  }
}
