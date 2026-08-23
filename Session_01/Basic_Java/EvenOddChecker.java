import java.util.Scanner;

/**
 * EvenOddChecker class
 * This program checks whether a number is even or odd
 */
public class EvenOddChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = getInput(scanner);
        checkEvenOdd(number);

        scanner.close();
    }

    /**
     * Method to take user input
     */
    private static int getInput(Scanner scanner) {
        System.out.print("Enter a number: ");
        return scanner.nextInt();
    }

    
     // Method to check even or odd
    
    private static void checkEvenOdd(int number) {
        if (number % 2 == 0) {
            System.out.println(number + " is Even");
        } else {
            System.out.println(number + " is Odd");
        }
    }
}