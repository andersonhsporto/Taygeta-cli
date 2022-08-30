package models;

public record Planet (int id, int width, int height) {


    public int getArea() {
        return width * height;
    }

}
