
import java.util.Scanner;
/*
	Do not modify this main function.
	*/
public class Solution {
/* Fill the main function to print the number of 7's between 1 to n*/
    public static int count = 0;

    public static void countseven(int n){
        for(int i = 0 ; i < n ; i++){
            seven(i);
        }
    }

    public static void seven(int val){
        while(val > 0){
            int rem = val % 10;
            if(rem == 7){
                count++;
            }
            val = val / 10;
        }
    }

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n = s.nextInt();
        countseven(n);
        System.out.println(count);
    }
}
