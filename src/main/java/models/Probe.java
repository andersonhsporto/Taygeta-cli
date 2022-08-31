package models;

public class Probe {

  private int id;

  private int x;

  private int y;

  private CompassRose.Compass direction;

  public Probe(int id, int x, int y, CompassRose.Compass direction) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.direction = direction;
  }

  public Probe(int x, int y, CompassRose.Compass direction) {
    this.id = -1;
    this.x = x;
    this.y = y;
    this.direction = direction;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Probe {" +
            " id= " + id +
            ", x= " + x +
            ", y= " + y +
            ", direction= " + direction +
            '}';
  }
}
