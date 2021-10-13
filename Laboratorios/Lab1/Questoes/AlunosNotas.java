import java.util.Scanner;

/**
* Laboratório de Programação 2 - Lab 1
*
* @author Pedro Manoel
*/
public class AlunosNotas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maiorNota = 0;
        int menorNota = 1000;
        int media = 0;
        int numNotas = 0;
        int numNotasAcima = 0;
        int numNotasAbaixo = 0;

        while(true) {
            String[] linha = sc.nextLine().split(" ");

            if (linha[0].equals("-")) { break; }

            int nota = Integer.parseInt(linha[1]); 

            // Somando a nota a média e aumentando a contagem de notas lidas
            media += nota;
            numNotas += 1;

            // Maior Nota
            if (nota > maiorNota ) { maiorNota = nota; }

            // Menor Nota
            if (nota < menorNota ) { menorNota = nota; }
            
            // Contabilizando as notas abaixo, acima ou igual a 700
            if (nota >= 700) { 
                numNotasAcima += 1; 
            } else {
                numNotasAbaixo += 1;
            }

        }
        // Exibindo os resultados
        System.out.format("maior: %d\n", maiorNota);
        System.out.format("menor: %d\n", menorNota);
        System.out.format("media: %d\n", media / numNotas);
        System.out.format("acima: %d\n", numNotasAcima);
        System.out.format("abaixo: %d", numNotasAbaixo);
        sc.close();
    }
}