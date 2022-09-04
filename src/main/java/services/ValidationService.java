package services;

import command.ColorWrapper;
import java.util.Collection;
import java.util.Optional;
import models.Planet;

public class ValidationService {

  public static boolean commandInPlanetSizeFormat(String command) {
    var commandArray = command.split("x");

    if (commandArray.length != 2) {
      return false;
    }
    try {
      int width = Integer.parseInt(commandArray[0]);
      int height = Integer.parseInt(commandArray[1]);

      if (width < 1 || height < 1) {
        return false;
      }
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  public static boolean planetExists(MissionControlService missionControlService) {
    if (missionControlService.getPlanets().isEmpty()) {
      System.out.println(ColorWrapper.red("Error there is no planets to add probes"));
      return false;
    } else {
      return true;
    }
  }

  public static boolean planetIsValid(String command, MissionControlService missionControlService) {
    if (planetExistsById(command, missionControlService)) {
      return !missionControlService.planetByIdIsFull(ParseService.id(command));
    } else {
      System.out.println(ColorWrapper.red("Error planet id: " + command + " does not exist"));
      return false;
    }
  }

  public static boolean planetExistsById(String command,
      MissionControlService missionControlService) {
    int planetId = ParseService.id(command);
    Collection<Planet> planets = missionControlService.getPlanets();

    return planetId >= 0 && planetId < planets.size();
  }

  public static boolean probeExists(
      Integer planetId, int probeId, MissionControlService missionControlService) {

    Optional<Planet> planet = missionControlService.getPlanetById(planetId);

    return planet.filter(value -> probeId >= 0 && probeId < value.getProbes().size()).isPresent();
  }

  public static boolean isValidSequence(String command) {
    return command.matches("^[MLR]+$");
  }
}
