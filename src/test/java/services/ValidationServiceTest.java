package services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidationServiceTest {

  @Test
  @DisplayName("Planet exist using a valid planet id")
  void truePlaneExistByid() {
    MissionControlService missionControlService = new MissionControlService();

    missionControlService.addPlanet("10x10");
    assertTrue(ValidationService.planetExistsById("0", missionControlService));
  }

  @Test
  @DisplayName("Planet exist using id that's not in the list")
  void falsePlaneExistByid() {
    MissionControlService missionControlService = new MissionControlService();

    missionControlService.addPlanet("55x10");
    assertFalse(ValidationService.planetExistsById("1", missionControlService));
  }

  @Test
  @DisplayName("Planet exist using valid id")
  void falsePlaneExistByidMultipleTrue() {
    MissionControlService missionControlService = new MissionControlService();

    missionControlService.addPlanet("55x10");
    missionControlService.addPlanet("1x1");
    assertTrue(ValidationService.planetExistsById("1", missionControlService));
  }

  @Test
  @DisplayName("Planet exist using id that's not in the list")
  void falsePlaneExistByidMultipleFalse() {
    MissionControlService missionControlService = new MissionControlService();

    missionControlService.addPlanet("55x10");
    missionControlService.addPlanet("1x1");
    assertFalse(ValidationService.planetExistsById("2", missionControlService));
  }

  @Test
  @DisplayName("Planet negative id is invalid")
  void falsePlanetExistNegativeId() {
    MissionControlService missionControlService = new MissionControlService();

    missionControlService.addPlanet("55x10");
    missionControlService.addPlanet("1x1");
    assertFalse(ValidationService.planetExistsById("-1", missionControlService));
  }

  @Test
  @DisplayName("Sequence string MLR is valid")
  void trueStringOnlyContainsCharactersMLR() {
    ValidationService validationService = new ValidationService();

    assertTrue(validationService.isValidSequence("MLR"));
  }

  @Test
  @DisplayName("Sequence string LMLMLMLMM is valid")
  void trueDefaultStringLMLMLMLMM() {
    ValidationService validationService = new ValidationService();

    assertTrue(validationService.isValidSequence("LMLMLMLMM"));
  }

  @Test
  @DisplayName("Big sequence string is valid")
  void bigStringIsValid() {
    ValidationService validationService = new ValidationService();

    assertTrue(validationService.isValidSequence(
        "LMLMLMLMMMMMMMMMMMMMMLMLMLMLMMMMMMMMMMMMMMLMLMLMLMMMMMMMMMMMMMM"));
  }

  @Test
  @DisplayName("Using invalid sequence string norte")
  void invalidSequenceNorte() {
    ValidationService validationService = new ValidationService();

    assertFalse(validationService.isValidSequence("norte"));
  }

  @Test
  @DisplayName("Empty sequence string is invalid")
  void emptyString() {
    ValidationService validationService = new ValidationService();

    assertFalse(validationService.isValidSequence(""));
  }

  @Test
  @DisplayName("Using invalid sequence string 123")
  void invalidString123() {
    ValidationService validationService = new ValidationService();

    assertFalse(validationService.isValidSequence("123"));
  }

  @Test
  @DisplayName("Lowercase sequence string is invalid")
  void lowercaseString() {
    ValidationService validationService = new ValidationService();

    assertFalse(validationService.isValidSequence("mlr"));
  }

  @Test
  @DisplayName("Sequence string with space is invalid")
  void stringWithSpace() {
    ValidationService validationService = new ValidationService();

    assertFalse(validationService.isValidSequence("M L R"));
  }

  @Test
  @DisplayName("Sequence string with tab is invalid")
  void stringWithTab() {
    ValidationService validationService = new ValidationService();

    assertFalse(validationService.isValidSequence("M\tL\tR"));
  }

  @Test
  @DisplayName("Sequence string with new line is invalid")
  void stringWithNewLine() {
    ValidationService validationService = new ValidationService();

    assertFalse(validationService.isValidSequence("M \n L \n R"));
  }

}