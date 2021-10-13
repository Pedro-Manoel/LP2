import java.util.Scanner;

/**
* Laboratório de Programação 2 - Lab 1
*
* @author Pedro Manoel
*/
public class DobroTriplo {
    public static void main(final String[] args) {
        int x = new Scanner(System.in).nextInt();
        System.out.format("dobro: %d, triplo: %d", x*2, x*3);
    }
}