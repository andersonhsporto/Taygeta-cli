package services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidationServiceTest {

  @Test
  @DisplayName("Plane exist by id true")
  void truePlaneExistByid() {
    MissionControlService missionControlService = new MissionControlService();
    missionControlService.addPlanet("10x10");
    assertTrue(ValidationService.planetExistsById("0", missionControlService));
  }

  @Test
  @DisplayName("Plane exist by id false")
  void falsePlaneExistByid() {
    MissionControlService missionControlService = new MissionControlService();
    missionControlService.addPlanet("55x10");
    assertFalse(ValidationService.planetExistsById("1", missionControlService));
  }

  @Test
  @DisplayName("Plane exist by id true multiple planets")
  void falsePlaneExistByidMultipleTrue() {
    MissionControlService missionControlService = new MissionControlService();
    missionControlService.addPlanet("55x10");
    missionControlService.addPlanet("1x1");
    assertTrue(ValidationService.planetExistsById("1", missionControlService));
  }

  @Test
  @DisplayName("Plane exist by id false multiple planets")
  void falsePlaneExistByidMultipleFalse() {
    MissionControlService missionControlService = new MissionControlService();
    missionControlService.addPlanet("55x10");
    missionControlService.addPlanet("1x1");
    assertFalse(ValidationService.planetExistsById("2", missionControlService));
  }

  @Test
  @DisplayName("Plane exist by negative id")
  void falsePlanetExistNegativeId() {
    MissionControlService missionControlService = new MissionControlService();
    missionControlService.addPlanet("55x10");
    missionControlService.addPlanet("1x1");
    assertFalse(ValidationService.planetExistsById("-1", missionControlService));
  }

}