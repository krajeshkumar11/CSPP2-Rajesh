
import java.util.Scanner;
/*
	Do not modify this main function.
	*/
public class Solution {
    /**
     * { var_description }
     */
    public static int count = 0;
    /**
     * { function_description }
     *
     * @param      n     { parameter_description }
     */
    public static void countseven(int n){
        for(int i = 0 ; i < n ; i++){
            seven(i);
        }
    }
    /**
     * { function_description }
     *
     * @param      val   The value
     */
    public static void seven(int val){
        while(val > 0){
            int rem = val % 10;
            if(rem == 7){
                count++;
            }
            val = val / 10;
        }
    }
    /**
     * { function_description }
     *
     * @param      args  The arguments
     */
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n = s.nextInt();
        countseven(n);
        System.out.println(count);
    }
}
