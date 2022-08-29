package command;

import models.Planet;
import org.junit.jupiter.api.DisplayName;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class TerminalTest {

    @org.junit.jupiter.api.Test
    void planetHasValidSize() {
        String command = "5x5";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        Planet test = Terminal.initPlanet();

        assertEquals(5, test.getWidth());
        assertEquals(5, test.getHeight());
    }
}