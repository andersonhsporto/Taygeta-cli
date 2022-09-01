package models;

import java.awt.Point;

public class Probe {

  private int id;

  private Point point;

  private CompassRose.Cardinal direction;

  public Probe(int id, int x, int y, CompassRose.Cardinal direction) {
    this.id = id;
    this.point = new Point(x, y);
    this.direction = direction;
  }

  public static Probe createDefault(int x, int y, CompassRose.Cardinal direction) {
    return new Probe(-1, x, y, direction);
  }

  public Point getPoint() {
    return point;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Probe{" +
        "id=" + id +
        ", point=" + point +
        ", direction=" + direction +
        '}';
  }
}
