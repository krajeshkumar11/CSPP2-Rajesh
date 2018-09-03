
import java.util.Scanner;
/*
	Do not modify this main function.
	*/
public class Solution {

    public static int gcd = 0;
    public static void gcd(int n1, int n2) {
        int max = 0;
        if(n1 > n2){
            max = n1;
        }else{
            max = n2;
        }
        for(int i = 1; i< max ; i++){
            if(n1 % i == 0 && n2 % 2 == 0 ){
                gcd = i;
            }
        }
    }
    public static void main(String[] args) {

        Scanner s=new Scanner(System.in);
        int n1 = s.nextInt();
        int n2 = s.nextInt();
        gcd(n1,n2);
        System.out.println(gcd);
    }
    /*
	Need to write the gcd function and print the output.
	*/
}
