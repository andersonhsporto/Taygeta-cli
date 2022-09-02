package command;

public class Message {

  public void greetings() {
    System.out.println(ColorWrapper.cyan("Welcome to Taygeta! CLI version"));
    System.out.println(ColorWrapper.cyan("The available commands are: add-planet, add-probe"));
  }

  public void error(String message) {
    System.out.println(ColorWrapper.red(message));
  }

  public void success(String message) {
    System.out.println(ColorWrapper.green(message));
  }

  public void defaultMessage(String message) {
    System.out.println(ColorWrapper.cyan(message));
  }

}
