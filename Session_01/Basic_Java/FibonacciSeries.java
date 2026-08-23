import java.util.Scanner;

// Created FibonacciSeries class with basic structure
public class FibonacciSeries {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //Added user input functionality

        System.out.print("Enter limit: ");
        int limit = sc.nextInt();

        if (limit <= 0) {
            System.out.println("Please enter a positive number");
            return;
        }
// Implemented Fibonacci series logic using loop
        int firstNumber = 0;
        int secondNumber = 1;

        System.out.println("Fibonacci Series:");
    // Added input validation for invalid values

        for (int i = 1; i <= limit; i++) {

            System.out.print(firstNumber + " ");

            int nextNumber = firstNumber + secondNumber;

            firstNumber = secondNumber;
            secondNumber = nextNumber;
        }

        sc.close();
    }
}
