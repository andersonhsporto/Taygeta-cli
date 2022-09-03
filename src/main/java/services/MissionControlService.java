package services;

import command.Message;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import models.Planet;
import models.Probe;

public class MissionControlService {

  private final Collection<Planet> planets;

  final Message message;

  public MissionControlService() {
    this.planets = new ArrayList<>();
    this.message = new Message();
  }

  public Collection<Planet> getPlanets() {
    return planets;
  }

  public void addProbeToPlanet(Probe probe, int planetId) {

    if (existProbeInCoordinates(probe, planetId)) {
      message.error("Probe already exists in this coordinates, the probe is not added");
      return;
    }
    if (coordinatesIsInvalid(probe, planetId)) {
      message.error("Invalid coordinates, the probe is not added");
      return;
    }
    message.success("Probe added to planet " + planetId);
    for (Planet planet : planets) {
      if (planet.getId() == planetId) {
        probe.setId(planet.getProbesCount());
        planet.addProbe(probe);
        System.out.println(planet);
      }
    }
  }

  public Optional<Planet> getPlanetById(int planetId) {
    for (Planet planet : planets) {
      if (planet.getId() == planetId) {
        return Optional.of(planet);
      }
    }
    return Optional.empty();
  }

  public boolean coordinatesIsInvalid(Probe probe, int planetId) {
    var planet = getPlanetById(planetId);
    var planetWidth = planet.get().getWidth();
    var planetHeight = planet.get().getHeight();

    if (probe.getPoint().getX() < 0 || probe.getPoint().getX() > planetWidth) {
      return true;
    }
    if (probe.getPoint().getY() < 0 || probe.getPoint().getY() > planetHeight) {
      return true;
    }
    return false;
  }
  public boolean existProbeInCoordinates(Probe probe, int planetId) {
    var planet = getPlanetById(planetId);

    for (Probe probeInPlanet : planet.get().getProbes()) {
      if (probeInPlanet.getPoint().equals(probe.getPoint())) {
        return true;
      }
    }
    return false;
  }

  public void addPlanet(String command) {
    Planet planet = Planet.createDefault(planets.size(), command);

    planets.add(planet);
    message.success("Planet ID " + (getPlanets().size() - 1) + " created");
  }

  private int getPlantsListSize() {
    return this.planets.size();
  }

  public int parseId(String string) { // TODO: add to a especific class
    try {
      return Integer.parseInt(string);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  public void listPlanets() {
    for (Planet planet : planets) {
      System.out.println(planet);
    }
  }

  public boolean planetIsFull(int planetId) {
    var planet = getPlanetById(planetId);

    return planet.get().isFull();
  }

}
