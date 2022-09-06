package services;

import exceptions.UndoCommandException;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import models.CompassRose.Cardinal;
import models.Planet;
import models.Probe;

public class MissionControlService {

  private final MessageService messageService;
  private final Collection<Planet> planets;

  public MissionControlService() {
    this.planets = new ArrayList<>();
    this.messageService = new MessageService();
  }

  public Collection<Planet> getPlanets() {
    return planets;
  }

  public void addProbeToPlanet(Probe probe, int planetId) {

    if (existProbeInCoordinates(probe, planetId)) {
      messageService.error("Probe already exists in this coordinates, the probe is not added");
      return;
    }
    if (!coordinatesIsValid(probe, planetId)) {
      messageService.error("Invalid coordinates, the probe is not added");
      return;
    }
    for (Planet planet : planets) {
      if (planet.getId() == planetId) {
        probe.setId(planet.getProbesCount());
        planet.addProbe(probe);
        System.out.println(planet);
      }
    }
    messageService.success("Probe added to planet " + planetId);
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
    return !(probe.getPoint().getY() < 1) && !(probe.getPoint().getY() > planetHeight);
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
    messageService.success("Planet ID " + (getPlanets().size() - 1) + " created");
  }

  private int getPlanetsListSize() {
    return this.planets.size();
  }

  public void listPlanets() {
    if (getPlanetsListSize() == 0) {
      messageService.error("No planets created");
      return;
    }
    for (Planet planet : planets) {
      System.out.println(planet);
    }
  }

  public void listProbes() {
    if (getPlanetsListSize() == 0) {
      messageService.error("No planets created");
      return;
    }
    for (Planet planet : planets) {
      planet.printProbes();
    }
  }

  public boolean planetByIdIsFull(int planetId) {
    var planet = getPlanetById(planetId);

    return planet.get().isFull();
  }


  //refactor
  public void moveProbe(
      Integer planetId, Integer probeId, String sequence) throws UndoCommandException {

    Probe probe = getPlanetById(planetId).get().getProbeById(probeId);
    Probe probeCopy;

    for (Planet planet : planets) {
      if (Objects.equals(planet.getId(), planetId)) {
        probeCopy = cloneUpdateProbe(probe, planet, sequence);
        planet.putProbe(probeId, probeCopy);
        System.out.println(planet.getProbes().get(probeId));
      }
    }
  }

  public Probe cloneUpdateProbe(
      Probe probe, Planet planet, String sequence) throws UndoCommandException {

    var newCardinal = probe.getCardinal();
    var newPoint = probe.getPoint();
    var originPoint = probe.getPoint();
    Probe newProbe;

    for (int i = 0; i < sequence.length(); i++) {
      switch (sequence.charAt(i)) {
        case 'L' -> newCardinal = rotateLeft(newCardinal);
        case 'R' -> newCardinal = rotateRight(newCardinal);
        case 'M' -> moveForward(newPoint, newCardinal, planet, originPoint);
      }
    }
    newProbe = new Probe(probe.getId(), newPoint, newCardinal);
    return newProbe;
  }

  public void moveForward(
      Point point, Cardinal cardinal, Planet planet, Point origin) throws UndoCommandException {

    switch (cardinal) {
      case NORTH -> moveNorth(point, planet);
      case SOUTH -> moveSouth(point, planet);
      case EAST -> moveEast(point, planet);
      case WEST -> moveWest(point, planet);
    }
    collision(planet, point, origin);
  }

  public void moveNorth(Point point, Planet planet) {
      if (point.getY() < planet.getHeight()) {
        point.translate(0, 1);
      } else if (point.getY() == planet.getHeight()) {
        point.setLocation(point.getX(), 1);
      }
  }

  public void moveSouth(Point point, Planet planet) {
      if (point.getY() > 1) {
        point.translate(0, -1);
      } else if (point.getY() == 1) {
        point.setLocation(point.getX(), planet.getHeight());
      }
  }

  public void moveEast(Point point, Planet planet) {
      if (point.getX() < planet.getWidth()) {
        point.setLocation(point.getX() + 1, point.getY());
      } else if (point.getX() == planet.getWidth()) {
        point.setLocation(1, point.getY());
      }
  }

  public void moveWest(Point point, Planet planet) {
      if (point.getX() > 1) {
        point.setLocation(point.getX() - 1, point.getY());
      } else if (point.getX() == 1) {
        point.setLocation(planet.getWidth(), point.getY());
      }
  }

  public Cardinal rotateLeft(Cardinal cardinal) {
    return switch (cardinal) {
      case NORTH -> Cardinal.WEST;
      case SOUTH -> Cardinal.EAST;
      case EAST -> Cardinal.NORTH;
      case WEST -> Cardinal.SOUTH;
    };
  }

  public Cardinal rotateRight(Cardinal cardinal) {
    return switch (cardinal) {
      case NORTH -> Cardinal.EAST;
      case SOUTH -> Cardinal.WEST;
      case EAST -> Cardinal.SOUTH;
      case WEST -> Cardinal.NORTH;
    };
  }

  public void collision(
      Planet planet, Point point, Point origin) throws UndoCommandException {

    for (Probe probeInPlanet : planet.getProbes().values()) {
      if (probeInPlanet.getPoint().equals(point) && !probeInPlanet.getPoint().equals(origin)) {
        messageService.error("Collision detected, the probe is not moved");
        throw new UndoCommandException("Collision detected");
      }
    }
  }



}

