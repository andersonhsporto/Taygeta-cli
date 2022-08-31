package command;

import models.Planet;
import models.Probe;

import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

public class Terminal {

    private static Planet planet;

    public void init () {
        Scanner        sc = new Scanner(System.in);
        MissionControl missionControl = new MissionControl();

        System.out.println("Welcome to Taygeta! CLI version");
        while(true) {
            System.out.println("Commands addp, add-probe");
            if (Objects.equals(sc.next(), "addp"))
            {
                addPlanet( missionControl );
            }
            if (Objects.equals(sc.next(), "add-probe"))
            {
                addProbe( missionControl );
            }
        }
//        sc.close( );
    }

    private void addProbe(MissionControl missionControl) {
        if (missionControl.getPlanets().isEmpty())
        {
            System.out.println("Error there is no planets to add probes");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter planet id number: > ");
        System.out.println(missionControl.getPlanets().size());
        while(sc.hasNext()) {
            if (planetExistsByID(sc.next(), missionControl)) {
                System.out.println("OK");
                makeProbe( missionControl );
                break;
            }
            System.out.println("Invalid planet id");
            System.out.println(missionControl.getPlanets().size());
            break;
        }
    }

    private void makeProbe(MissionControl missionControl)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter probe x coordinate: > ");
        int x = sc.nextInt();
        System.out.print("Enter probe y coordinate: > ");
        int y = sc.nextInt();
        System.out.print("Enter probe direction: > ");
        int direction = sc.nextInt();
        Probe probe = new Probe(1, x, y, direction);
        missionControl.getPlanets().
    }
    private int parseId(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    private boolean planetExistsByID(String command, MissionControl missionControl) {
        int planetId = parseId(command);

        return planetId >= 0 && planetId < missionControl.getPlanets().size();
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
