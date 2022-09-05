package services;

import command.ColorWrapper;

public class MessageService {

  public void greetings() {
    System.out.println(ColorWrapper.cyan("Welcome to Taygeta! CLI version"));
  }

  public void error(String message) {
    System.out.println(ColorWrapper.red("Error:\t" + message));
  }

  public void success(String message) {
    System.out.println(ColorWrapper.green(message));
  }

  public void defaultMessage(String message) {
    System.out.print(ColorWrapper.cyan(message));
  }

}
