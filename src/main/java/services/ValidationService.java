package services;

import command.ColorWrapper;
import command.MissionControl;
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

      if (width < 0 || height < 0) {
        return false;
      }
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  public static boolean planetExistsById(String command, MissionControl missionControl) {
    int planetId = missionControl.parseId(command);
    Collection<Planet> planets = missionControl.getPlanets();

    return planetId >= 0 && planetId < planets.size();
  }

  public static boolean planetExists(MissionControl missionControl) {
    if (missionControl.getPlanets().isEmpty()) {
      System.out.println(ColorWrapper.red("Error there is no planets to add probes"));
      return false;
    } else {
      return true;
    }
  }
}
