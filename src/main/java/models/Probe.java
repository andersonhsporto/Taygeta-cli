package models;

import java.awt.Point;
import java.util.Objects;
import models.CompassRose.Cardinal;

public class Probe {

  private int id;

  private final Point point;

  private final CompassRose.Cardinal cardinal;

  public Probe(int id, int x, int y, CompassRose.Cardinal cardinal) {
    this.id = id;
    this.point = new Point(x, y);
    this.cardinal = cardinal;
  }

  public Probe(int id, Point point, Cardinal cardinal) {
    this.id = id;
    this.point = point;
    this.cardinal = cardinal;
  }


  public static Probe createDefault(int x, int y, CompassRose.Cardinal direction) {
    return new Probe(-1, x, y, direction);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Point getPoint() {
    return point;
  }

  public Cardinal getCardinal() {
    return cardinal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Probe probe = (Probe) o;
    return id == probe.id && Objects.equals(point, probe.point)
        && cardinal == probe.cardinal;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, point, cardinal);
  }

  @Override
  public String toString() {
    return "Probe {" +
        "id=" + id +
        ", point=" + point +
        ", direction=" + cardinal +
        '}';
  }
}
