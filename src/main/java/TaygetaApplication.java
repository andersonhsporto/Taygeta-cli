import java.util.Scanner;

public class TaygetaApplication {
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um texto: ");
        while(sc.hasNext()) {
            System.out.println("String: " + sc.next());
        }
        sc.close();
    }
}
