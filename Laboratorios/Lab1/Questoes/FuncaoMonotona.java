import java.util.Scanner;

/**
* Laboratório de Programação 2 - Lab 1
*
* @author Pedro Manoel
*/
public class FuncaoMonotona {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[4];
        
        nums[0] = sc.nextInt();
        nums[1] = sc.nextInt();
        nums[2] = sc.nextInt();
        nums[3] = sc.nextInt();

        if (nums[0] < nums[1] && nums[1] < nums[2] && nums[2] < nums[3]) {
            System.out.print("POSSIVELMENTE ESTRITAMENTE CRESCENTE");
        } else if (nums[0] > nums[1] && nums[1] > nums[2] && nums[2] > nums[3]) {
            System.out.print("POSSIVELMENTE ESTRITAMENTE DECRESCENTE");
        } else {
            System.out.print("FUNCAO NAO ESTRITAMENTE CRES/DECR");
        }
        sc.close();
    
    }
}