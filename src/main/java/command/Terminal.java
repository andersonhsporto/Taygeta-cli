package command;

import java.util.Scanner;
import models.Planet;
import models.Probe;

public class Terminal {

    private static Planet planet;

    public void init () {
        Scanner        scanner = new Scanner(System.in);
        MissionControl missionControl = new MissionControl();

        System.out.println("Welcome to Taygeta! CLI version");
        while(true) {
            System.out.println("Commands add-planet, add-probe");
            invokeCommand(scanner.next(), missionControl);
        }
//        scanner.close( );
    }

    private void invokeCommand(String command, MissionControl missionControl) {
        switch (command) {
            case "add-planet" -> makePlanet(missionControl);
            case "add-probe" -> makeProbe(missionControl);
        }
    }

    private void makeProbe(MissionControl missionControl) {
        if (missionControl.getPlanets().isEmpty())
        {
            System.out.println(Colors.ANSI_RED + "Error there is no planets to add probes" + Colors.ANSI_RESET);
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter planet id number: > ");
        while (true) {
            String command = scanner.next();
            if (planetExistsByID(command, missionControl)) {
                makeProbe( command, missionControl);
                break;
            }
            System.out.println("Invalid planet id");
            System.out.println(missionControl.getPlanets().size());
            break;
        }
    }

    private void makeProbe(String command, MissionControl missionControl)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter probe x coordinate: > ");
        int x = scanner.nextInt();
        System.out.print("Enter probe y coordinate: > ");
        int y = scanner.nextInt();
        System.out.print("Enter probe direction: > ");
        int direction = scanner.nextInt();
        Probe probe = new Probe(1, x, y, direction);
        System.out.println(command);
        missionControl.addProbeToPlanet(probe, parseId(command));

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

    public static void makePlanet(MissionControl missionControl) {
        System.out.print("Enter planet area width and height: (example: 5x5) > ");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();

        if (isValidPlanetSize(command)) {
            missionControl.addPlanet(command);
            System.out.println("Plane added ID: " + (missionControl.getPlanets().size() - 1));
        } else {
            makePlanet(missionControl);
            System.out.println("Invalid planet size");
        }
    }
}
