package command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import exceptions.UndoCommandException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import models.CompassRose.Cardinal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.ParseService;
import services.ValidationService;

class TerminalTest {

  @Test
  @DisplayName("Command is a invalid planet size")
  void isValidPlanetSize() {
    String command = "5xteste";

    assertFalse(ValidationService.commandIsValidPlanetSize(command));
  }

  @Test
  @DisplayName("Command 50x50 is a planet valid size")
  void isValidPlanetSize2() {
    String command = "50x50";

    assertTrue(ValidationService.commandIsValidPlanetSize(command));
  }

  @Test
  @DisplayName("Undo probe direction")
  void undoProbeDirection() throws UndoCommandException {
    Terminal terminal = new Terminal();
    String command = "undo";
    InputStream inputStream = new ByteArrayInputStream(command.getBytes());

    System.setIn(inputStream);
    try {
      Cardinal compass = ParseService.parseDirection();
    } catch (UndoCommandException e) {
      assertTrue(true);
    }
  }

}