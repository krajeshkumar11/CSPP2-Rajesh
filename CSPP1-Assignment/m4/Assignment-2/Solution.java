import java.util.*;
public class Solution {
	/* Fill the main function to print resultant of addition of matrices*/
	public static Scanner sc = new Scanner(System.in);
    public static int[][] matrix(){
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] ma = new int[m][n];
        for (int i=0; i<m ; i++ ) {
             for (int j=0;j<n ;j++ ) {
                 ma[i][j] = sc.nextInt();
             }
         }
         return ma;
    }

    public static int[][] emptymatrix(int m, int n){
        int[][] ma = new int[m][n];
        for (int i=0; i<m ; i++ ) {
             for (int j=0;j<n ;j++ ) {
                ma[i][j] = -1;
            }
        }
        return ma;
    }

    public static void add(int[][] m1, int[][] m2){
        if(m1.length == m2.length && m1[0].length == m2[0].length){
            int m = m1.length;
            int n = m1[0].length;
            int[][] ma = emptymatrix(m, n);
            for (int i=0; i<m ; i++ ) {
                for (int j=0;j<n ;j++ ) {
                    ma[i][j] = m1[i][j] + m2[i][j];
                }
            }
            for (int i=0; i<m ; i++ ) {
                for (int j=0;j<n ;j++ ) {
                    System.out.print(ma[i][j]);
                    if(j < n-1){
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }else{
            System.out.println("not possible");
        }
    }
    public static void main(String[] args) {
        int[][] first = matrix();
        int[][] second = matrix();
        add(first, second);
	}
}
