package services;

import static org.junit.jupiter.api.Assertions.*;

import command.MissionControl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidationServiceTest {

  @Test
  @DisplayName("Plane exist by id true")
  void truePlaneExistByid() {
    MissionControl missionControl = new MissionControl();
    missionControl.addPlanet("10x10");
    assertTrue(ValidationService.planetExistsById("0", missionControl));
  }

  @Test
  @DisplayName("Plane exist by id false")
  void falsePlaneExistByid() {
    MissionControl missionControl = new MissionControl();
    missionControl.addPlanet("55x10");
    assertFalse(ValidationService.planetExistsById("1", missionControl));
  }

  @Test
  @DisplayName("Plane exist by id true multiple planets")
  void falsePlaneExistByidMultipleTrue() {
    MissionControl missionControl = new MissionControl();
    missionControl.addPlanet("55x10");
    missionControl.addPlanet("1x1");
    assertTrue(ValidationService.planetExistsById("1", missionControl));
  }

  @Test
  @DisplayName("Plane exist by id false multiple planets")
  void falsePlaneExistByidMultipleFalse() {
    MissionControl missionControl = new MissionControl();
    missionControl.addPlanet("55x10");
    missionControl.addPlanet("1x1");
    assertFalse(ValidationService.planetExistsById("2", missionControl));
  }

  @Test
  @DisplayName("Plane exist by negative id")
  void falsePlanetExistNegativeId() {
    MissionControl missionControl = new MissionControl();
    missionControl.addPlanet("55x10");
    missionControl.addPlanet("1x1");
    assertFalse(ValidationService.planetExistsById("-1", missionControl));
  }

}