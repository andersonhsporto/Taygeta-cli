package command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TerminalTest {

    @Test
    @DisplayName("Planet has invalid size")
    void isValidPlanetSize() {
        String command = "5xteste";
        assertFalse(Terminal.isValidPlanetSize(command));
    }

    @Test
    @DisplayName("Planet has valid size")
    void isValidPlanetSize2() {
        String command = "50x50";
        assertTrue(Terminal.isValidPlanetSize(command));
    }
}