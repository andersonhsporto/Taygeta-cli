package command;

import models.Planet;

import java.util.Objects;
import java.util.Scanner;

public class Terminal {

    private static Planet planet;

    public void init () {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Taygeta! CLI version");
        while(sc.hasNext()) {
            System.out.println("String: " + sc.next());
            if (Objects.equals(sc.next(), "addp"))
            {
                planet = initPlanet( );
                System.out.println(planet.toString());
            }
        }
        sc.close( );
    }

    public static boolean isValidPlanetSize(String command) {
        String[] commandArray = command.split("x");

        if (commandArray.length != 2) {
            return false;
        }
        try {
            int width = Integer.parseInt(commandArray[0]);
            int height = Integer.parseInt(commandArray[1]);

            if (width < 0 || height < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static Planet initPlanet() {
        System.out.println("Enter planet area width and height: (example: 5x5)");
        Scanner sc = new Scanner(System.in);
        String command = sc.next();

        if (isValidPlanetSize(command)) {
            planet = new Planet(command);
        } else {
            System.out.println("Invalid planet size");
            initPlanet( );
        }
        return planet;
    }
}
