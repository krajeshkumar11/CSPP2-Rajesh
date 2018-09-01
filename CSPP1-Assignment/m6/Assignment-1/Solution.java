import java.util.Scanner;
/**
 * Write a java program to find the odd composite numbers
 * between 2 and n where n is given as an input.
 *
 * @author :
 */
public final class Solution {
    /**
    * Empty constructor.
    */
    private Solution() {
        //not used
    }
    /**
     * Prints the odd composite numbers between 2 and n.
     *
     * @param      n     n value
     */
    static void oddComposites(final int n) {
	   for (int i = 2; i < n ; i++) {
            if(!prime(i) && i % 2 != 0){
                System.out.println(i);
            }
        }
    }

    public static boolean prime(int val){
        boolean flag = true;
        for (int i = 2; i< val/2 ; i++ ) {
            if(val %i == 0){
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
    * main method as driver program.
    * @param args is the parameter for this method
    */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        oddComposites(n);
    }
}

