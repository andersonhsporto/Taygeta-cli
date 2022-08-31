package models;

import java.util.ArrayList;
import java.util.Collection;

public class Planet {
    private int id;

    private int width;

    private int height;

    private Collection<Probe> probes;

    public Planet(int id, String command) {
        var commandArray = command.split("x");

        this.id = id;
        this.width = Integer.parseInt(commandArray[0]);
        this.height = Integer.parseInt(commandArray[1]);
        this.probes = new ArrayList<>();
    }

    public int getArea() {
        return width * height;
    }

    public void addProbe(Probe probe) {
        probes.add(probe);
        System.out.println(probe.toString());
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Collection<Probe> getProbes() {
        return probes;
    }

    @Override
    public String toString() {
        return " Planet {" +
                " id = " + id +
                ", width = " + width +
                ", height = " + height +
                '}';
    }
}
