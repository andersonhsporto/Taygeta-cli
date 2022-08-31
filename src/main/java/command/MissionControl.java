package command;

import models.Planet;

import java.util.ArrayList;
import java.util.Collection;

public class MissionControl {

    private Collection<Planet>  planets;

    public Collection<Planet> getPlanets() {
        return planets;
    }

    public MissionControl() {
        this.planets = new ArrayList<>();
    }

    public void addPlanet(String command) {
        Planet planet = new Planet(planets.size(), command);

        planets.add(planet);
    }

}
