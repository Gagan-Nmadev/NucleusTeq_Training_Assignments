
import java.util.Scanner;

/**
 * AreaCalculator class
 * This program calculates area of different shapes based on user choice
 * Demonstrates OOP, input validation, and clean structure
 */
public class AreaCalculator {

    // Constant valuesgg
    private static final double PI = 3.14159;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose shape to calculate area:");
        System.out.println("1. Circle");
        System.out.println("2. Rectangle");
        System.out.println("3. Triangle");

        int choice = scanner.nextInt();

        // Use switch case method.

        switch (choice) {
            case 1:
                calculateCircleArea(scanner);
                break;

            case 2:
                calculateRectangleArea(scanner);
                break;

            case 3:
                calculateTriangleArea(scanner);
                break;

            default:
                System.out.println("Invalid choice!");
        }

        scanner.close();
    }

    /**
     * Method to calculate circle area
     */
    private static void calculateCircleArea(Scanner scanner) {
        System.out.print("Enter radius: ");
        double radius = scanner.nextDouble();

        if (radius <= 0) {
            System.out.println("Radius must be positive!");
            return;
        }

        double area = PI * radius * radius;
        System.out.println("Area of Circle: " + area);
    }

    /**
     * Method to calculate rectangle area
     */
    private static void calculateRectangleArea(Scanner scanner) {
        System.out.print("Enter length: ");
        double length = scanner.nextDouble();

        System.out.print("Enter width: ");
        double width = scanner.nextDouble();

        if (length <= 0 || width <= 0) {
            System.out.println("Values must be positive!");
            return;
        }

        double area = length * width;
        System.out.println("Area of Rectangle: " + area);
    }

    /**
     * Method to calculate triangle area
     */
    private static void calculateTriangleArea(Scanner scanner) {
        System.out.print("Enter base: ");
        double base = scanner.nextDouble();

        System.out.print("Enter height: ");
        double height = scanner.nextDouble();

        if (base <= 0 || height <= 0) {
            System.out.println("Values must be positive!");
            return;
        }

        double area = 0.5 * base * height;
        System.out.println("Area of Triangle: " + area);
    }
}



