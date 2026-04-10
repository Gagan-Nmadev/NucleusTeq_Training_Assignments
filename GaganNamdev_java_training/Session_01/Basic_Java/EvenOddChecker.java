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