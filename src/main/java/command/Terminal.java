package command;

import java.util.Scanner;

public class Terminal {

    public void init () {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter ");
        while(sc.hasNext()) {
            System.out.println("String: " + sc.next());
        }
        sc.close();
    }

    public void initPlane(){
        System.out.println("Enter planet area width and height: (example: 5x5)");
    }
}
