package command;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.MessageService;

class MessageServiceTest {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  // Reassigns standard output stream to a new stream with ByteArrayOutputStream
  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  // Restore initial standard output stream
  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

  @Test
  @DisplayName("Test greetings message")
  void testGreetingMessageIsCyanWelcomeToTaygeta() {
    MessageService messageService = new MessageService();
    String defaultCyanGreetings = "[36mWelcome to Taygeta! CLI version\u001B[0m";

    messageService.greetings();
    assertEquals(defaultCyanGreetings, outputStreamCaptor.toString().trim());
  }

  @Test
  @DisplayName("Test Red error message")
  void TestIfErrorIsRedMessage() {
    MessageService messageService = new MessageService();
    String defaultRedError = "[31mError:\tInvalid planet area\u001B[0m";

    messageService.error("Invalid planet area");
    assertEquals(defaultRedError, outputStreamCaptor.toString().trim());
  }

  @Test
  @DisplayName("Test Green success message")
  void success() {
    MessageService messageService = new MessageService();
    String defaultGreenSuccess = "[32mProbe successfully added\u001B[0m";

    messageService.success("Probe successfully added");
    assertEquals(defaultGreenSuccess, outputStreamCaptor.toString().trim());
  }

  @Test
  @DisplayName("Test Cyan default message")
  void defaultMessage() {
    MessageService messageService = new MessageService();
    String defaultCyanMessage = "[36mEnter planet area width and height: (example: 5x5) > \u001B[0m";

    messageService.defaultMessage("Enter planet area width and height: (example: 5x5) > ");
    assertEquals(defaultCyanMessage, outputStreamCaptor.toString().trim());
  }
}