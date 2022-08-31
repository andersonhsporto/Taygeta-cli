package models;

public class Probe {

    private int id;

    private int x;

    private int y;

    private int direction;

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
