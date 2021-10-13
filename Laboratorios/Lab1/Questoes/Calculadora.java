import java.util.Scanner;

/**
* Laboratório de Programação 2 - Lab 1
*
* @author Pedro Manoel
*/
public class Calculadora {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String operador = sc.nextLine();

        switch (operador) {
            case "+" -> System.out.format("RESULTADO: %.1f", (sc.nextFloat() + sc.nextFloat()));
            case "-" -> System.out.format("RESULTADO: %.1f", (sc.nextFloat() - sc.nextFloat()));
            case "*" -> System.out.format("RESULTADO: %.1f", (sc.nextFloat() * sc.nextFloat()));
            case "/" -> {
                float n1 = sc.nextFloat();
                float n2 = sc.nextFloat();
                if (n2 == 0.0) {
                    System.out.print("ERRO");
                } else {
                    System.out.format("RESULTADO: %.1f", (n1 / n2));
                }
            }
            default -> System.out.print("ENTRADA INVALIDA");
        }
        sc.close();
    }
}