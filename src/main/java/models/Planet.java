package models;

import java.util.ArrayList;
import java.util.Collection;

public class Planet {
  private final int width;
  private final int height;
  private final int id;

  private boolean full;
  private final Collection<Probe> probes;

  public Planet(int id, String command) {
    var commandArray = command.split("x");

    this.id = id;
    this.width = Integer.parseInt(commandArray[0]);
    this.height = Integer.parseInt(commandArray[1]);
    this.full = false;
    this.probes = new ArrayList<>();
  }

  public int getArea() {
    return width * height;
  }

  void printProbes() {
    for (Probe probe : probes) {
      System.out.println(probe);
    }
  }

  public void addProbe(Probe probe) {
    probes.add(probe);
    printProbes();
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

  public int getProbesCount() {
    return probes.size();
  }

  public boolean isFull() {
    return full;
  }

  @Override
  public String toString() {
    return " Planet {" + " id = " + id + ", width = " + width + ", height = " + height + '}';
  }
}
