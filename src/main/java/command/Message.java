package command;

public class Message {

  public static void greetings() {
    System.out.println(ColorWrapper.cyan("Welcome to Taygeta! CLI version"));
    System.out.println(ColorWrapper.cyan("The available commands are: add-planet, add-probe"));
  }
}
