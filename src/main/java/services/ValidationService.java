package services;

import command.ColorWrapper;
import java.util.Collection;
import models.Planet;

public class ValidationService {

  public static boolean commandIsValidPlanetSize(String command) {
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

  public static boolean planetExistsById(String command, MissionControlService missionControlService) {
    int planetId = missionControlService.parseId(command);
    Collection<Planet> planets = missionControlService.getPlanets();

    return planetId >= 0 && planetId < planets.size();
  }

  public static boolean planetExists(MissionControlService missionControlService) {
    if (missionControlService.getPlanets().isEmpty()) {
      System.out.println(ColorWrapper.red("Error there is no planets to add probes"));
      return false;
    } else {
      return true;
    }
  }
}
