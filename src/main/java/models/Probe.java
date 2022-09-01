package models;

public class Probe {

  private int id;

  private int x;

  private int y;

  private CompassRose.Cardinal direction;

  public Probe(int id, int x, int y, CompassRose.Cardinal direction) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.direction = direction;
  }

  public static Probe createDefault(int x, int y, CompassRose.Cardinal direction) {
    return new Probe(-1, x, y, direction);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
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
