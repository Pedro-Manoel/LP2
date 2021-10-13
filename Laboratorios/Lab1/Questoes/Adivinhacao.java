import java.util.Scanner;

/**
* Laboratório de Programação 2 - Lab 1
*
* @author Pedro Manoel
*/
public class Adivinhacao {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numCerto = Integer.parseInt(sc.nextLine());
    int numTentativa = 0;

    while (true) {
      numTentativa = Integer.parseInt(sc.nextLine());
      if (numTentativa > numCerto) {
        System.out.println("MAIOR");
      } else if (numTentativa < numCerto) {
        System.out.println("MENOR");
      } else {
        System.out.println("ACERTOU");
        break;
      }
    }

    sc.close();
  }
}