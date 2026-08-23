
import java.util.Scanner;

public class FactorialCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int num = sc.nextInt();

        if (num < 0) {
            System.out.println("Negative number ka factorial nahi hota");
        } else {
            int fact = 1;

            for (int i = 1; i <= num; i++) {
                fact = fact * i;
            }

            System.out.println("Factorial = " + fact);
        }

        sc.close();
    }
}