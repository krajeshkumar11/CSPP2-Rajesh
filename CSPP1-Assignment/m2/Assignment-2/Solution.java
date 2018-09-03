import java.util.Scanner;
import java.lang.Math;
public class Solution {
	/**
	 * { function_description }
	 *
	 * @param      a     { parameter_description }
	 * @param      b     { parameter_description }
	 * @param      c     { parameter_description }
	 * @param      si    { parameter_description }
	 *
	 * @return     { description_of_the_return_value }
	 */
	public static double rootsOfQuadraticEquation(int a, int b,int c, char si){
		if(si == '+'){
			return (-b + Math.sqrt(Math.pow(b,2) - (4 * a * c)))/(2 * a);
		}else{
			return (-b - Math.sqrt(Math.pow(b,2) - (4 * a * c)))/(2 * a);
		}
	}
	/**
	 * { function_description }
	 *
	 * @param      args  The arguments
	 */
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
