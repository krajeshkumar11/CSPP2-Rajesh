import java.util.Scanner;

public class Solution {
	/*
	Do not modify this main function.
	*/
    public static int power(int base, int expo){
        int sum = 1;
        while(expo > 0){
            sum = sum * base;
            expo -= 1;
        }
        return sum;
    }
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
        int base = s.nextInt();
        int exponent = s.nextInt();
        int result=power(base,exponent);
        System.out.println(result);
	}
	/*
	Need to write the power function and print the output.
	*/
}
