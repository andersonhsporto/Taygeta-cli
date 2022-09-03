package command;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MessageTest {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

  @Test
  @DisplayName("Test greetings message")
  void testGreetingMessageIsCyanWelcomeToTaygeta() {
    Message message = new Message();
    String defaultCyanGreetings = "[36mWelcome to Taygeta! CLI version\u001B[0m";

    message.greetings();
    assertEquals(defaultCyanGreetings, outputStreamCaptor.toString().trim());
  }

  @Test
  @DisplayName("Test Red error message")
  void TestIfErrorIsRedMessage() {
    Message message = new Message();
    String defaultRedError = "[31mInvalid planet area\u001B[0m";

    message.error("Invalid planet area");
    assertEquals(defaultRedError, outputStreamCaptor.toString().trim());
  }

  @Test
  @DisplayName("Test Green sucess message")
  void success() {
    Message message = new Message();
    String defaultGreenSuccess = "[32mProbe successfully added\u001B[0m";

    message.success("Probe successfully added");
    assertEquals(defaultGreenSuccess, outputStreamCaptor.toString().trim());
  }

  @Test
  @DisplayName("Test Cyan default message")
  void defaultMessage() {
    Message message = new Message();
    String defaultCyanMessage = "[36mEnter planet area width and height: (example: 5x5) > \u001B[0m";

    message.defaultMessage("Enter planet area width and height: (example: 5x5) > ");
    assertEquals(defaultCyanMessage, outputStreamCaptor.toString().trim());
  }
}