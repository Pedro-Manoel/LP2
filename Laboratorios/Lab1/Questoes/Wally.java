import java.util.Scanner;

/**
* Laboratório de Programação 2 - Lab 1
*
* @author Pedro Manoel
*/
public class Wally {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        
        while(loop) {
            String[] listaNomes = sc.nextLine().split(" ");

            for (int x = listaNomes.length - 1; x >= 0 ; x--) {
                if (listaNomes[x].equals("wally")) {
                    loop = false;
                    break; 
                } else if (listaNomes[x].length() == 5) {
                    System.out.println(listaNomes[x]);
                    break;
                } else if (x == 0) {
                    System.out.println("?");
                }    
            }
        }
        sc.close();
    }
}