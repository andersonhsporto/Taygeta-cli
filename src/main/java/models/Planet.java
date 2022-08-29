package models;

public class Planet {

    private String name;
    private int width;
    private int height;

    public Planet(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Planet(String command) {
        String[] commandArray = command.split("x");

        this.width = Integer.parseInt(commandArray[0]);
        this.height = Integer.parseInt(commandArray[1]);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }


}
