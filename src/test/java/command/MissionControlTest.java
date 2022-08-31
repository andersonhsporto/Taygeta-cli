package command;

import models.Probe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MissionControlTest {

    @Test
    @DisplayName("Test add Planet of size 5x5")
    void add5x5Planet() {
        MissionControl missionControl = new MissionControl();
        missionControl.addPlanet("5x5");
        assertEquals(1, missionControl.getPlanets().size());
    }

    @Test
    @DisplayName("Add one probe to planet")
    void addOneProbeToPlanet() {
        MissionControl missionControl = new MissionControl();
        missionControl.addPlanet("10x10");
        missionControl.addProbeToPlanet(new Probe(1, 0, 0, 1), 0);
        assertEquals(1, missionControl.getPlanets().size());
    }

}