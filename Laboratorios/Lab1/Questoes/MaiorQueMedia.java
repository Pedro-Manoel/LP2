import java.util.Scanner;

/**
* Laboratório de Programação 2 - Lab 1
*
* @author Pedro Manoel
*/
public class MaiorQueMedia {

    public static int calculaMedia(String[] numsStr) {
        int media = 0;

        for (String s : numsStr)
            media += Integer.parseInt(s);

        return media / numsStr.length;
    }

    public static void main(String[] args) {
        String[] numsStr = new Scanner(System.in).nextLine().split(" ");
        int media = calculaMedia(numsStr);

        for (String s : numsStr) {
            int n = Integer.parseInt(s);
            if (n > media)
                System.out.print(n + " ");
        }
    }
}