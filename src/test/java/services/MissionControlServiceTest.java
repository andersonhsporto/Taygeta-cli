package services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import models.CompassRose;
import models.CompassRose.Cardinal;
import models.Planet;
import models.Probe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MissionControlServiceTest {

  @Test
  @DisplayName("Test add Planet of size 5x5")
  void add5x5Planet() {
    MissionControlService missionControlService = new MissionControlService();
    missionControlService.addPlanet("5x5");
    assertEquals(1, missionControlService.getPlanets().size());
  }

  @Test
  @DisplayName("Add one probe to planet")
  void addOneProbeToPlanet() {
    MissionControlService missionControlService = new MissionControlService();
    missionControlService.addPlanet("10x10");
    missionControlService.addProbeToPlanet(new Probe(1, 0, 0, Cardinal.NORTH), 0);
    assertEquals(1, missionControlService.getPlanets().size());
  }

  @Test
  @DisplayName("Size of planets list is 5")
  void sizeOfPlanetsListIs5() {
    MissionControlService missionControlService = new MissionControlService();
    missionControlService.addPlanet("5x5");
    missionControlService.addPlanet("7x1");
    missionControlService.addPlanet("1x3");
    missionControlService.addPlanet("4x9");
    missionControlService.addPlanet("42x42");
    assertEquals(5, missionControlService.getPlanets().size());
  }

  @Test
  @DisplayName("Size of planets list is 0")
  void sizeOfPlanetsListIs0() {
    MissionControlService missionControlService = new MissionControlService();
    assertEquals(0, missionControlService.getPlanets().size());
  }

  @Test
  @DisplayName("Size of planets list is 1")
  void sizeOfPlanetsListIs1() {
    MissionControlService missionControlService = new MissionControlService();
    missionControlService.addPlanet("5x5");
    assertEquals(1, missionControlService.getPlanets().size());
  }


  @Test
  @DisplayName("Move probe by sequence string")
  void moveProbeBySequenceString() {
    MissionControlService missionControlService = new MissionControlService();

    Point point = new Point(1, 2);
    CompassRose.Cardinal cardinal = Cardinal.NORTH;
    Planet planet = new Planet(0, 5, 5);

    System.out.println("START: " + point + " " + cardinal);
    Cardinal newenum = missionControlService.movementsSequence(point, cardinal, "LMLMLMLMM",
        planet);
    System.out.println(point);
    System.out.println("FINAL: " + point + " " + newenum);
  }
}