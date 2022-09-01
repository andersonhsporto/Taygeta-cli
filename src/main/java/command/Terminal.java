package command;

import exceptions.UndoCommandException;
import java.util.Scanner;
import models.CompassRose;
import models.Planet;
import models.Probe;

public class Terminal {

  private static Planet planet;

  public static void makePlanet(MissionControl missionControl) throws UndoCommandException {
    System.out.print("Enter planet area width and height: (example: 5x5) > ");
    Scanner scanner = new Scanner(System.in);
    String command = scanner.next();

    if (isValidPlanetSize(command)) {
      System.out.println("Plane added ID: " + (missionControl.getPlanets().size() - 1));
      missionControl.addPlanet(command);
    } else if (command.equals("undo")) {
      throw new UndoCommandException("Undo command add-planet");
    } else {
      System.out.println("Invalid planet size");
      makePlanet(missionControl);
    }
  }

  public static boolean isValidPlanetSize(String command) {
    var commandArray = command.split("x");

    if (commandArray.length != 2) {
      return false;
    }
    try {
      int width = Integer.parseInt(commandArray[0]);
      int height = Integer.parseInt(commandArray[1]);

      if (width < 0 || height < 0) {
        return false;
      }
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  public void init() {
    Scanner scanner = new Scanner(System.in);
    MissionControl missionControl = new MissionControl();

    System.out.println("Welcome to Taygeta! CLI version");
    while (true) {
      System.out.println("Commands add-planet, add-probe");
      try {
        invokeCommand(scanner.next(), missionControl);
      } catch (UndoCommandException e) {
        continue;
      }
    }
  }

  private void invokeCommand(String command, MissionControl missionControl)
      throws UndoCommandException {
    switch (command) {
      case "add-planet" -> makePlanet(missionControl);
      case "add-probe" -> makeProbe(missionControl);
      default -> System.out.println(Colors.red("Invalid command"));
    }
  }

  private void makeProbe(MissionControl missionControl) throws UndoCommandException {
    if (!planetExists(missionControl)) {
      return;
    }
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter planet id number: > ");
    while (true) {
      String command = scanner.next();
      if (planetExistsById(command, missionControl)) {
        addProbe(command, missionControl);
        break;
      } else if (command.equals("undo")) {
        throw new UndoCommandException("Undo command add-probe");
      } else {
        System.out.println("Invalid planet id");
      }
      System.out.println(Colors.red("Error planet with id " + command + " does not exist"));
      break;
    }
  }

  private boolean planetExists(MissionControl missionControl) {
    if (missionControl.getPlanets().isEmpty()) {
      System.out.println(Colors.red("Error there is no planets to add probes"));
      return false;
    } else {
      return true;
    }
  }

  private void addProbe(String command, MissionControl missionControl) throws UndoCommandException {
    var x = addProbeParameter("x coordinate");
    var y = addProbeParameter("y coordinate");
    var direction = parseDirection();
    var probe = new Probe(x, y, direction);

    missionControl.addProbeToPlanet(probe, parseId(command));
  }

  CompassRose.Compass parseDirection() throws UndoCommandException {
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
        System.out.println(Colors.red("Invalid direction"));
      }
    }
  }

  private int addProbeParameter(String message) throws UndoCommandException {
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
        System.out.println(Colors.red("Error invalid " + message));
      }
    }
  }

  private int parseId(String string) {
    try {
      return Integer.parseInt(string);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  private boolean planetExistsById(String command, MissionControl missionControl) {
    int planetId = parseId(command);

    return planetId >= 0 && planetId < missionControl.getPlanets().size();
  }
}
