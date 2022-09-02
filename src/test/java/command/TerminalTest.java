package command;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import exceptions.UndoCommandException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import models.CompassRose.Cardinal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.ParseService;

class TerminalTest {

//  @Test
//  @DisplayName("Planet has invalid size")
//  void isValidPlanetSize() {
//    String command = "5xteste";
//    assertFalse(ValidationService.isValidPlanetSize(command));
//  }
//
//  @Test
//  @DisplayName("Planet has valid size")
//  void isValidPlanetSize2() {
//    String command = "50x50";
//
//    assertTrue(Terminal.isValidPlanetSize(command));
//  }

  @Test
  @DisplayName("Parse North Direction Test")
  void parseNortDirection() throws UndoCommandException {
    Terminal terminal = new Terminal();
    String command = "N";
    InputStream inputStream = new ByteArrayInputStream(command.getBytes());

    System.setIn(inputStream);
    try {
      var compass = ParseService.parseDirection();
      assertEquals(Cardinal.NORTH, compass);
    } catch (UndoCommandException e) {
      assertTrue(true);

    }
  }

  @Test
  @DisplayName("Parse South Direction Test")
  void parseSouthDirection() throws UndoCommandException {
    Terminal terminal = new Terminal();
    String command = "Sul";
    InputStream inputStream = new ByteArrayInputStream(command.getBytes());

    System.setIn(inputStream);
    try {
      Cardinal compass = ParseService.parseDirection();
      assertEquals(Cardinal.SOUTH, compass);
    } catch (UndoCommandException e) {
      assertTrue(true);
    }
  }

  @Test
  @DisplayName("Parse East Direction Test")
  void parseEastDirection() throws UndoCommandException {
    Terminal terminal = new Terminal();
    String command = "leste";
    InputStream inputStream = new ByteArrayInputStream(command.getBytes());

    System.setIn(inputStream);
    try {
      Cardinal compass = ParseService.parseDirection();
      assertEquals(Cardinal.EAST, compass);
    } catch (UndoCommandException e) {
      assertTrue(true);
    }
  }

  @Test
  @DisplayName("Parse West Direction Test")
  void parseWestDirection() throws UndoCommandException {
    Terminal terminal = new Terminal();
    String command = "W";
    InputStream inputStream = new ByteArrayInputStream(command.getBytes());

    System.setIn(inputStream);
    try {
      Cardinal compass = ParseService.parseDirection();
      assertEquals(Cardinal.WEST, compass);
    } catch (UndoCommandException e) {
      assertTrue(true);
    }
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

  @Test
  @DisplayName("Parse valid string to int")
  void parseStringToInt() throws UndoCommandException {
    Terminal terminal = new Terminal();
    String command = "5";
    int number = ParseService.parseId(command);

    Assertions.assertEquals(5, number);
  }

  @Test
  @DisplayName("Parse invalid string to int")
  void parseInvalidStringToInt() throws UndoCommandException {
    Terminal terminal = new Terminal();
    String command = "111Invalid2";

    Assertions.assertEquals(-1, ParseService.parseId(command));
  }

  @Test
  @DisplayName("Parse invalid string to int")
  void parseInvalidStringToInt2() throws UndoCommandException {
    Terminal terminal = new Terminal();
    String command = "12a";

    Assertions.assertEquals(-1, ParseService.parseId(command));
  }
}