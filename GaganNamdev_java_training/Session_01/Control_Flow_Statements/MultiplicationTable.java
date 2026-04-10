import java.util.Scanner;
public class MultiplicationTable {

    public static void main(String[] args) {

        // Scanner for user input
        Scanner sc = new Scanner(System.in);

        // Taking input number
        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        System.out.println("\nMultiplication Table of " + number + ":\n");
    
        //  for loop to generate table
        for (int i = 1; i <= 10; i++) {

            int result = number * i;

            System.out.println(number + " x " + i + " = " + result);
        }

        sc.close(); 
    }
}

