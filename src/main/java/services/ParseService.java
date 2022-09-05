package services;

import command.ColorWrapper;
import exceptions.UndoCommandException;
import java.util.Optional;
import java.util.Scanner;
import models.CompassRose;
import models.Probe;

public class ParseService {

  private final MessageService messageService;

  public ParseService() {
    this.messageService = new MessageService();
  }

  public static Probe probe() throws UndoCommandException {
    var x = probeParameter("x coordinate");
    var y = probeParameter("y coordinate");
    var direction = probeDirection();
    var probe = Probe.createDefault(x, y, direction); //TODO: refactor add probe to mission control

    return probe;
  }

  public static CompassRose.Cardinal probeDirection() throws UndoCommandException {
    Scanner scanner = new Scanner(System.in);
    String command;

    System.out.print("Enter probe initial direction: > ");
    command = scanner.next();
    if (CompassRose.isValidCardinal(command)) {
        return CompassRose.parseCardinal(command);
    } else if (command.equals("undo")) {
        throw new UndoCommandException("Undo command add-probe");
    } else {
        System.out.println(ColorWrapper.red("Invalid direction"));
        return probeDirection();
    }
  }

  private static int probeParameter(String message) throws UndoCommandException {
    Scanner scanner = new Scanner(System.in);
    String command;
    int value;

    while (true) {
      System.out.print("Enter probe " + message + ": > ");
      command = scanner.next();
      value = id(command);
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

  public static int id(String string) {
    try {
      return Integer.parseInt(string);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  public Optional<Integer> planetID(
      MissionControlService missionControlService) throws UndoCommandException {

    Scanner scanner = new Scanner(System.in);
    String command;

    messageService.defaultMessage("Enter planet id number: > ");
    command = scanner.next();
    if ("undo".equals(command)) {
      throw new UndoCommandException("Undo command add-probe");
    }
    if (ValidationService.planetIsValid(command, missionControlService)) {
      return Optional.of(Integer.parseInt(command));
    } else {
      messageService.error("Invalid planet id");
      return Optional.empty();
    }
  }

  public String parseSequenceCommands() throws UndoCommandException {
    Scanner scanner = new Scanner(System.in);
    String command;

    messageService.defaultMessage("Enter sequence of commands: > ");
    command = scanner.next();
    if ("undo".equals(command)) {
      throw new UndoCommandException("Undo command add-probe");
    }
    if (ValidationService.isValidSequence(command)) {
      return command;
    } else {
      messageService.error("Invalid sequence of commands");
      return parseSequenceCommands();
    }
  }

  public int parseProbeId(
      Integer planetId, MissionControlService missionControlService) throws UndoCommandException {

    int probeId = probeParameter("id");

    if (ValidationService.probeExists(planetId, probeId, missionControlService)) {
      return probeId;
    } else {
      messageService.error("Invalid probe id");
      throw new UndoCommandException("Undo command move-probe");
    }
  }

}
