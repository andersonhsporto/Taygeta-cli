package command;

import exceptions.UndoCommandException;
import java.util.Scanner;
import models.Probe;
import services.MissionControlService;
import services.ParseService;
import services.ValidationService;

public class Terminal {

  final Message message;

  public Terminal() {
    this.message = new Message();
  }

  public void init() {
    var scanner = new Scanner(System.in);
    var missionControl = new MissionControlService();

    message.greetings();
    while (true) {
      System.out.println("Commands add-planet, add-probe and exit");
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
      case "exit" -> System.exit(0);
      default -> message.error("Invalid command");
    }
  }

  public void makePlanet(
      MissionControlService missionControlService) throws UndoCommandException {

    message.defaultMessage("Enter planet area width and height: (example: 5x5) > ");
    Scanner scanner = new Scanner(System.in);
    String command = scanner.next();

    if (ValidationService.commandIsValidPlanetSize(command)) {
      missionControlService.addPlanet(command);
    } else if (command.equals("undo")) {
      throw new UndoCommandException("Undo command add-planet");
    } else {
      message.error("Invalid planet area");
      makePlanet(missionControlService);
    }
  }

  private void makeProbe(MissionControlService missionControlService) throws UndoCommandException {
    if (!ValidationService.planetExists(missionControlService)) return;

    Scanner scanner = new Scanner(System.in);
    String  command;

    message.defaultMessage("Enter planet id number: > ");
    while (true) {
      command = scanner.next();
      if (ValidationService.planetExistsById(command, missionControlService)) {
        addProbeToPlanet(command, missionControlService);
        break;
      } else if (command.equals("undo")) {
        throw new UndoCommandException("Undo command add-probe");
      } else {
        message.error("Invalid planet id");
      }
      message.error("Error planet with id " + command + " does not exist");
      break;
    }
  }

  private void addProbeToPlanet(
      String command, MissionControlService missionControlService) throws UndoCommandException {

    if (!missionControlService.planetIsFull(ParseService.parseId(command))) {
      Probe probe = ParseService.parseProbe();

      missionControlService.addProbeToPlanet(probe, ParseService.parseId(command));
    } else {
      message.error("Error planet with id " + command + " is full");
    }
  }

}
