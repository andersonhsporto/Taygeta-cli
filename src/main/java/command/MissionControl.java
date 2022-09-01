package command;

import java.util.ArrayList;
import java.util.Collection;
import models.Planet;
import models.Probe;

public class MissionControl {

  private final Collection<Planet> planets;

  public MissionControl() {
    this.planets = new ArrayList<>();
  }

  public Collection<Planet> getPlanets() {
    return planets;
  }

  public void addProbeToPlanet(Probe probe, int planetId) {

    System.out.println("Adding probe to planet " + planetId);
    for (Planet planet : planets) {
      if (planet.getId() == planetId) {
        probe.setId(planet.getProbesCount());
        planet.addProbe(probe);
        System.out.println(planet);
      }
    }
  }

  public void addPlanet(String command) {
    Planet planet = new Planet(planets.size(), command);
    planets.add(planet);
  }

}
