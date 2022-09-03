package models;

import java.util.HashMap;
import java.util.Map;

public class Planet {
  private final int id;
  private int width;
  private int height;
  private boolean full;
  private Map<Integer, Probe> probes;

  public Planet(int id, int width, int height) {
    this.id = id;
    this.width = width;
    this.height = height;
    this.full = false;
    this.probes = new HashMap<Integer, Probe>();
  }

  public static Planet createDefault(int id, String command) {
    var commandArray = command.split("x");
    var width = Integer.parseInt(commandArray[0]);
    var height = Integer.parseInt(commandArray[1]);

    return new Planet(id, width, height);
  }

  public Long getArea() {
    return (long) width * height;
  }

  void printProbes() {
    for (Map.Entry<Integer, Probe> entry : probes.entrySet()) {
      System.out.println(entry.getValue());
    }
  }

  public void addProbe(Probe probe) {
    probes.put(probes.size(), probe);
    if (probes.size() == getArea()) {
      full = true;
    }
    printProbes();
  }


  public Integer getId() {
    return id;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Map<Integer, Probe> getProbes() {
    return probes;
  }

  public Probe getProbeById(int id) {
    return probes.get(id);
  }

  public int getProbesCount() {
    return probes.size();
  }

  public boolean isFull() {
    return full;
  }

  public void putProbe(Integer id, Probe probe) {
    this.probes.put(id, probe);
  }

  @Override
  public String toString() {
    return " Planet {" + " id = " + id + ", width = " + width + ", height = " + height + '}';
  }
}
