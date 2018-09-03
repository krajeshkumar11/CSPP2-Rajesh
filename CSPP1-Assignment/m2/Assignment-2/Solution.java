import java.util.Scanner;
import java.lang.Math;
public class Solution {
	/*
	Do not modify this main function.
	*/
	public static double rootsOfQuadraticEquation(int a, int b,int c, char si){
		if(si == '+'){
			return (-b + Math.sqrt(Math.pow(b,2) - (4 * a * c)))/(2 * a);
		}else{
			return (-b - Math.sqrt(Math.pow(b,2) - (4 * a * c)))/(2 * a);
		}
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt();
		int b = scan.nextInt();
		int c = scan.nextInt();
		System.out.print(rootsOfQuadraticEquation(a, b, c, '+'));
		System.out.print(" ");
		System.out.print(rootsOfQuadraticEquation(a, b, c, '-'));
	}
}
