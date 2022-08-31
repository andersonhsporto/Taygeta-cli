package models;

public class CompassRose {

  public enum Compass {
    NORTH,
    SOUTH,
    EAST,
    WEST
  }

  public static boolean isValidDirection(String command) {
    String undercaseCommand = command.toLowerCase();

    switch (undercaseCommand) {
      case "north", "norte", "n", "south", "sul", "s" -> {
        return true;
      }
      case "east", "leste", "e", "l", "west", "oeste", "w", "o" -> {
        return true;
      }
      default -> {
        return false;
      }
    }
  }

  public static Compass parseDirection(String command) {
    String undercaseCommand = command.toLowerCase();

    switch (undercaseCommand) {
      case "north", "norte", "n" -> {
        return Compass.NORTH;
      }
      case "south", "sul", "s" -> {
        return Compass.SOUTH;
      }
      case "east", "leste", "e" -> {
        return Compass.EAST;
      }
      case "west", "oeste", "w", "o" -> {
        return Compass.WEST;
      }
      default -> {
        return null;
      }
    }
  }
}
