package command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColorsTest {

  @Test
  @DisplayName("red() should return red string")
  void red() {
    String string = "red";

    assertEquals("\u001B[31mred\u001B[0m", Colors.red(string));
  }

  @Test
  @DisplayName("green() should return green string")
  void green() {
    String string = "green";

    assertEquals("\u001B[32mgreen\u001B[0m", Colors.green(string));
  }

  @Test
  @DisplayName("red() should not return green string")
  void redNotGreen() {
    String string = "red";

    assertNotEquals("\u001B[32mgreen\u001B[0m", Colors.red(string));
  }

  @Test
  @DisplayName("green() should not return red string")
  void greenNotRed() {
    String string = "green";

    assertNotEquals("\u001B[31mred\u001B[0m", Colors.green(string));
  }
}