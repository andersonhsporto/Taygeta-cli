package command;

import models.Planet;
import models.Probe;

import java.util.ArrayList;
import java.util.Collection;

public class MissionControl {

    private final Collection<Planet> planets;

    public MissionControl() {
        this.planets = new ArrayList<>();
    }

    public Collection<Planet> getPlanets() {
        return planets;
    }

    public void addProbeToPlanet(Probe probe, int planetID) {

        System.out.println("Adding probe to planet " + planetID);
        for (Planet planet : planets) {
            if (planet.getId() == planetID) {
                planet.addProbe(probe);
                System.out.println(planet);
            }
        }
    }

    public void addPlanet(String command) {

        Planet planet = new Planet(planets.size(), command);

        planets.add(planet);
    }


}
