package command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberInputStream;
import java.io.LineNumberReader;
import models.CompassRose.Compass;
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
        missionControl.addProbeToPlanet(new Probe(1, 0, 0, Compass.NORTH), 0);
        assertEquals(1, missionControl.getPlanets().size());
    }

    @Test
    @DisplayName("Plane exist by id true")
    void truePlaneExistByid() {
        MissionControl missionControl = new MissionControl();
        missionControl.addPlanet("10x10");
        assertTrue(missionControl.planetExistsById("0"));
    }

    @Test
    @DisplayName("Plane exist by id false")
    void falsePlaneExistByid() {
        MissionControl missionControl = new MissionControl();
        missionControl.addPlanet("55x10");
        assertFalse(missionControl.planetExistsById("1"));
    }

    @Test
    @DisplayName("Plane exist by id true multiple planets")
    void falsePlaneExistByidMultipleTrue() {
        MissionControl missionControl = new MissionControl();
        missionControl.addPlanet("55x10");
        missionControl.addPlanet("1x1");
        assertTrue(missionControl.planetExistsById("1"));
    }

    @Test
    @DisplayName("Plane exist by id false multiple planets")
    void falsePlaneExistByidMultipleFalse() {
        MissionControl missionControl = new MissionControl();
        missionControl.addPlanet("55x10");
        missionControl.addPlanet("1x1");
        assertFalse(missionControl.planetExistsById("2"));
    }

    @Test
    @DisplayName("Plane exist by negative id")
    void falsePlanetExistNegativeId() {
        MissionControl missionControl = new MissionControl();
        missionControl.addPlanet("55x10");
        missionControl.addPlanet("1x1");
        assertFalse(missionControl.planetExistsById("-1"));
    }

    @Test
    @DisplayName("Size of planets list is 5")
    void sizeOfPlanetsListIs5() {
        MissionControl missionControl = new MissionControl();
        missionControl.addPlanet("5x5");
        missionControl.addPlanet("7x1");
        missionControl.addPlanet("1x3");
        missionControl.addPlanet("4x9");
        missionControl.addPlanet("42x42");
        assertEquals(5, missionControl.getPlanets().size());
    }

    @Test
    @DisplayName("Size of planets list is 0")
    void sizeOfPlanetsListIs0() {
        MissionControl missionControl = new MissionControl();
        assertEquals(0, missionControl.getPlanets().size());
    }

    @Test
    @DisplayName("Size of planets list is 1")
    void sizeOfPlanetsListIs1() {
        MissionControl missionControl = new MissionControl();
        missionControl.addPlanet("5x5");
        assertEquals(1, missionControl.getPlanets().size());
    }


}