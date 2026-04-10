

import java.util.Scanner;

public class PrimeCheck {

    public static void main(String[] args) {

        // Scanner object for user input
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        boolean isPrime = true;

        //  0 and 1 are not prime numbers
        if (number <= 1) {
            isPrime = false;
        } 
        else {
            //  check divisibility from 2 to number/2
            for (int i = 2; i <= number / 2; i++) {

                if (number % i == 0) {
                    isPrime = false;
                    break; // stop loop if factor found
                }
            }
        }
        
        //  final result using if-else
        if (isPrime) {
            System.out.println(number + " is a PRIME number.");
        } else {
            System.out.println(number + " is NOT a PRIME number.");
        }

        sc.close(); 
    }
}

