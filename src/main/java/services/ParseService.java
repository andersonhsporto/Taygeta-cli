package services;

import command.ColorWrapper;
import exceptions.UndoCommandException;
import java.util.Scanner;
import models.CompassRose;
import models.Probe;

public class ParseService {

  public static Probe parseProbe()
      throws UndoCommandException {

    var x = addProbeParameter("x coordinate");
    var y = addProbeParameter("y coordinate");
    var direction = parseDirection();
    var probe = Probe.createDefault(x, y, direction); //TODO: refactor add probe to mission control

    return probe;
  }


  public static CompassRose.Cardinal parseDirection() throws UndoCommandException {
    Scanner scanner = new Scanner(System.in);
    String command;

    while (true) {
      System.out.print("Enter probe direction: > ");
      command = scanner.next();
      if (CompassRose.isValidDirection(command)) {
        return CompassRose.parseDirection(command);
      } else if (command.equals("undo")) {
        throw new UndoCommandException("Undo command add-probe");
      } else {
        System.out.println(ColorWrapper.red("Invalid direction"));
      }
    }
  }

  private static int addProbeParameter(String message) throws UndoCommandException {
    Scanner scanner = new Scanner(System.in);
    String command;
    int value;

    while (true) {
      System.out.print("Enter probe " + message + ": > ");
      command = scanner.next();
      value = parseId(command);
      if (command.equals("undo")) {
        throw new UndoCommandException("Undo command " + message);
      }
      if (value != -1) {
        return value;
      } else {
        System.out.println(ColorWrapper.red("Error invalid " + message));
      }
    }
  }

  public static int parseId(String string) {
    try {
      return Integer.parseInt(string);
    } catch (NumberFormatException e) {
      return -1;
    }
  }


}
