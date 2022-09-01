package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColorWrapperTest {

  @Test
  @DisplayName("red() should return red string")
  void red() {
    String string = "red";

    assertEquals("\u001B[31mred\u001B[0m", ColorWrapper.red(string));
  }

  @Test
  @DisplayName("green() should return green string")
  void green() {
    String string = "green";

    assertEquals("\u001B[32mgreen\u001B[0m", ColorWrapper.green(string));
  }

  @Test
  @DisplayName("red() should not return green string")
  void redNotGreen() {
    String string = "red";

    assertNotEquals("\u001B[32mgreen\u001B[0m", ColorWrapper.red(string));
  }

  @Test
  @DisplayName("green() should not return red string")
  void greenNotRed() {
    String string = "green";

    assertNotEquals("\u001B[31mred\u001B[0m", ColorWrapper.green(string));
  }
}