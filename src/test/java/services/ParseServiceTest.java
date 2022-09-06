package services;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import command.Terminal;
import exceptions.UndoCommandException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.MissionControlService.Cardinal;

class ParseServiceTest {

  private final ParseService parseService = new ParseService();
  @Test
  @DisplayName("Parse North Direction Test")
  void parseNorthDirection() throws UndoCommandException {
    Terminal terminal = new Terminal();
    String command = "N";
    InputStream inputStream = new ByteArrayInputStream(command.getBytes());

    System.setIn(inputStream);
    try {
      var compass = parseService.probeDirection();
      Assertions.assertEquals(Cardinal.NORTH, compass);
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
      Cardinal compass = parseService.probeDirection();
      Assertions.assertEquals(Cardinal.SOUTH, compass);
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
      Cardinal compass = parseService.probeDirection();
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
      Cardinal compass = parseService.probeDirection();
      assertEquals(Cardinal.WEST, compass);
    } catch (UndoCommandException e) {
      assertTrue(true);
    }
  }


  @Test
  @DisplayName("Parse valid string to int")
  void parseStringToInt() throws UndoCommandException {
    Terminal terminal = new Terminal();
    String command = "5";
    int number = ParseService.id(command);

    Assertions.assertEquals(5, number);
  }

  @Test
  @DisplayName("Parse invalid string to int")
  void parseInvalidStringToInt() throws UndoCommandException {
    Terminal terminal = new Terminal();
    String command = "111Invalid2";

    Assertions.assertEquals(-1, ParseService.id(command));
  }

  @Test
  @DisplayName("Parse invalid string to int")
  void parseInvalidStringToInt2() throws UndoCommandException {
    Terminal terminal = new Terminal();
    String command = "12a";

    Assertions.assertEquals(-1, ParseService.id(command));
  }

}