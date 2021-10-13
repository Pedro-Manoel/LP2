import java.util.Scanner;

/**
* Laboratório de Programação 2 - Lab 1
*
* @author Pedro Manoel
*/
public class GastosMensais {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] meses =  sc.nextLine().split(" ");
        String[] gastos =  sc.nextLine().split(" ");
        String mesValor = sc.nextLine();

        for (int i = 0; i < meses.length; i++) {
            if (meses[i].equals(mesValor)) {
                System.out.print(gastos[i]);
            }
        } 

        sc.close();
    }
}