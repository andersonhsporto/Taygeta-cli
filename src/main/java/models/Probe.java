package models;

public class Probe {

  private final int id;

  private final int x;

  private final int y;

  private final int direction;

  public Probe(int id, int x, int y, int direction) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.direction = direction;
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
