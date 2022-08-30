package command;

import models.Planet;

import java.util.Objects;
import java.util.Scanner;

public class Terminal {

    private static Planet planet;

    public void init () {
        Scanner        sc = new Scanner(System.in);
        MissionControl missionControl = new MissionControl();

        System.out.println("Welcome to Taygeta! CLI version");
        while(sc.hasNext()) {
            if (Objects.equals(sc.next(), "addp"))
            {
                addPlanet( missionControl );
            }
        }
        sc.close( );
    }

    private void addProbe(MissionControl missionControl) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter planet id number: > ");
        while(sc.hasNext()) {
            int planetId = sc.nextInt();
            if (planetId >= 0 && planetId < missionControl.planets.size()) {
                planet = missionControl.planets.get(planetId);
                break;
            }
            System.out.println("Invalid planet id");
            System.out.print("Enter planet id number: > ");
        }
    }

    public static boolean isValidPlanetSize(String command) {
        var commandArray = command.split("x");

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

    public static void addPlanet(MissionControl missionControl) {
        System.out.print("Enter planet area width and height: (example: 5x5) > ");
        Scanner sc = new Scanner(System.in);
        String command = sc.next();

        if (isValidPlanetSize(command)) {
            missionControl.addPlanet(command);
        } else {
            System.out.println("Invalid planet size");
            addPlanet(missionControl);
        }
    }
}
