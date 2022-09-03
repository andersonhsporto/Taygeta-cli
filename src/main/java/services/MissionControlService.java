package services;

import command.Message;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import models.CompassRose.Cardinal;
import models.Planet;
import models.Probe;

public class MissionControlService {

  private Collection<Planet> planets;

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
    if (!coordinatesIsValid(probe, planetId)) {
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

  public boolean coordinatesIsValid(Probe probe, int planetId) {
    var planet = getPlanetById(planetId);
    var planetWidth = planet.get().getWidth();
    var planetHeight = planet.get().getHeight();

    if (probe.getPoint().getX() < 1 || probe.getPoint().getX() > planetWidth) {
      return false;
    }
    if (probe.getPoint().getY() < 1 || probe.getPoint().getY() > planetHeight) {
      return false;
    }
    return true;
  }
  public boolean existProbeInCoordinates(Probe probe, int planetId) {
    var planet = getPlanetById(planetId);

    for (Probe probeInPlanet : planet.get().getProbes().values()) {
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

  private int getPlanetsListSize() {
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

  public boolean planetByIdIsFull(int planetId) {
    var planet = getPlanetById(planetId);

    return planet.get().isFull();
  }

  public void moveProbe(Integer planeId, Integer probeId, String sequence) {
    Probe dummy = new Probe(0, 2, 3 , Cardinal.NORTH);

    for (Planet planet : planets) {
      if (Objects.equals(planet.getId(), planeId)) {
        planet.putProbe(probeId, dummy);
        System.out.println(planet.getProbes().get(probeId));
      }
    }

  }

}
