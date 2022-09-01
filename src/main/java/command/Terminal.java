package command;

import exceptions.UndoCommandException;
import java.util.Scanner;
import models.CompassRose;
import models.Probe;
import services.MissionControlService;
import services.ValidationService;

public class Terminal {

  public void init() {
    var scanner = new Scanner(System.in);
    var missionControl = new MissionControlService();

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

  private void invokeCommand(
      String command, MissionControlService missionControlService) throws UndoCommandException {

    switch (command) {
      case "add-planet" -> makePlanet(missionControlService);
      case "add-probe" -> makeProbe(missionControlService);
      default -> System.out.println(ColorWrapper.red("Invalid command"));
    }
  }

  public static void makePlanet(MissionControlService missionControlService) throws UndoCommandException {
    System.out.print("Enter planet area width and height: (example: 5x5) > ");
    Scanner scanner = new Scanner(System.in);
    String command = scanner.next();

    if (ValidationService.commandIsValidPlanetSize(command)) {
      missionControlService.addPlanet(command);
    } else if (command.equals("undo")) {
      throw new UndoCommandException("Undo command add-planet");
    } else {
      System.out.println(ColorWrapper.red("Invalid planet area"));
      makePlanet(missionControlService);
    }
  }

  private void makeProbe(MissionControlService missionControlService) throws UndoCommandException {
    if (!ValidationService.planetExists(missionControlService)) return;

    Scanner scanner = new Scanner(System.in);
    String  command;

    System.out.print("Enter planet id number: > ");
    while (true) {
      command = scanner.next();
      if (ValidationService.planetExistsById(command, missionControlService)) {
        addProbeToPlanet(command, missionControlService);
        break;
      } else if (command.equals("undo")) {
        throw new UndoCommandException("Undo command add-probe");
      } else {
        System.out.println("Invalid planet id");
      }
      System.out.println(ColorWrapper.red("Error planet with id " + command + " does not exist"));
      break;
    }
  }

  private void addProbeToPlanet(String command, MissionControlService missionControlService) throws UndoCommandException {
    Probe probe = parseProbe(command, missionControlService);

    if (!missionControlService.planetIsFull(parseId(command))) {
      missionControlService.addProbeToPlanet(probe, parseId(command));
    } else {
      System.out.println(ColorWrapper.red("Error planet with id " + command + " is full"));
    }
  }

  private Probe parseProbe(String command, MissionControlService missionControlService) throws UndoCommandException {
    var x = addProbeParameter("x coordinate");
    var y = addProbeParameter("y coordinate");
    var direction = parseDirection();
    var probe = new Probe(x, y, direction); //TODO: refactor add probe to mission control

    return probe;
  }

  public CompassRose.Compass parseDirection() throws UndoCommandException {
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
        System.out.println(ColorWrapper.red("Error invalid " + message));
      }
    }
  }

  public int parseId(String string) { // TODO: add to a especific class
    try {
      return Integer.parseInt(string);
    } catch (NumberFormatException e) {
      return -1;
    }
  }


}
