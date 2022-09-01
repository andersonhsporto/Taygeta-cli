package models;

public class CompassRose {

  public enum Cardinal {
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

  public static Cardinal parseDirection(String command) {
    String undercaseCommand = command.toLowerCase();

    switch (undercaseCommand) {
      case "north", "norte", "n" -> {
        return Cardinal.NORTH;
      }
      case "south", "sul", "s" -> {
        return Cardinal.SOUTH;
      }
      case "east", "leste", "e", "l" -> {
        return Cardinal.EAST;
      }
      case "west", "oeste", "w", "o" -> {
        return Cardinal.WEST;
      }
      default -> {
        return null;
      }
    }
  }
}
