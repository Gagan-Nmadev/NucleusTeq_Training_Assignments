

import java.util.Scanner;

public class PatternPrinter {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size: ");
        int size = sc.nextInt();

        if (size <= 0) {
            System.out.println("Enter positive number");
            return;
        }

        System.out.println("\nTriangle Pattern:");
        printTriangle(size);

        System.out.println("\nSquare Pattern:");
        printSquare(size);

        sc.close();
    }

    // Method for triangle
    private static void printTriangle(int size) {

        for (int row = 1; row <= size; row++) {

            for (int col = 1; col <= row; col++) {
                System.out.print("* ");
            }

            System.out.println();
        }
    }

    