package command;

import models.Planet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

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