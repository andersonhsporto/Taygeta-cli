package models;

public class CompassRose {

  public static boolean isValidDirection(String command) {
    String underscoreCommand = command.toLowerCase();

    return switch (underscoreCommand) {
      case "north", "south", "east", "west", "norte", "sul", "leste", "oeste" -> true;
      case "n", "s", "e", "l", "w", "o" -> true;
      default -> false;
    };
  }

  public static Cardinal parseDirection(String command) {
    String underscoreCommand = command.toLowerCase();

    return switch (underscoreCommand) {
      case "north", "norte", "n" -> Cardinal.NORTH;
      case "south", "sul", "s" -> Cardinal.SOUTH;
      case "east", "leste", "e", "l" -> Cardinal.EAST;
      case "west", "oeste", "w", "o" -> Cardinal.WEST;
      default -> throw new IllegalArgumentException("Invalid direction");
    };
  }

  public enum Cardinal {
    NORTH,
    SOUTH,
    EAST,
    WEST
  }

}
