package command;

import exceptions.UndoCommandException;
import java.util.Scanner;
import services.MissionControlService;
import services.ParseService;
import services.ValidationService;

public class Terminal {

  final Message message;
  final ParseService parseService;

  public Terminal() {
    this.message = new Message();
    this.parseService = new ParseService();
  }

  public void init() {
    var scanner = new Scanner(System.in);
    var missionControl = new MissionControlService();

    message.greetings();
    while (true) {
      System.out.println("Commands add-planet, add-probe and exit");
      try {
        receiveCommand(scanner.next(), missionControl);
      } catch (UndoCommandException e) {
      }
    }
  }

  private void receiveCommand(
      String command, MissionControlService missionControlService) throws UndoCommandException {

    switch (command) {
      case "add-planet" -> makePlanet(missionControlService);
      case "add-probe", "move-probe" -> planetExists(command, missionControlService);
      case "exit" -> System.exit(0);
      default -> message.error("Invalid command");
    }
  }

  private void planetExists(
      String command, MissionControlService missionControlService) throws UndoCommandException {

    if (ValidationService.planetExists(missionControlService)) {
      switch (command) {
        case "add-probe" -> makeProbe(missionControlService);
        case "move-probe" -> moveProbe(missionControlService);
        default -> message.error("Invalid command");
      }
    }
  }

  public void moveProbe(MissionControlService missionControlService) throws UndoCommandException {
    var planetId = parseService.parsePlanetID(missionControlService);

    if (planetId.isPresent()) {
      int probeId = parseService.parseProbeId(planetId.get(), missionControlService);
      String sequenceCommands = parseService.parseSequenceCommands();
      missionControlService.moveProbe(planetId.get(), probeId, sequenceCommands);
    }
  }


  private void makePlanet(
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
      var command = parseService.parsePlanetID(missionControlService);

      if (command.isPresent())
        addProbeToPlanet(command.get(), missionControlService);
  }

  private void addProbeToPlanet(
      Integer planetId, MissionControlService missionControlService) throws UndoCommandException {

      var probe = ParseService.parseProbe();

      missionControlService.addProbeToPlanet(probe, planetId);
  }

}
