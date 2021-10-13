import java.util.Scanner;

/**
* Laboratório de Programação 2 - Lab 1
*
* @author Pedro Manoel
*/
public class Media7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double num1 = sc.nextDouble();
        double num2 = sc.nextDouble();

        if (((num1 + num2) / 2) >= 7) {
            System.out.print("pass: True!");
        } else {
            System.out.print("pass: False!");
        }
        sc.close();
    }
}