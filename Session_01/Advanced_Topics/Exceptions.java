
/*What is Exception Handling?

Exception handling is a mechanism in Java used to handle runtime errors so that the normal flow of the program is not disturbed.

An exception is an unexpected problem that occurs during program execution and stops the normal flow of the program.

Without exception handling:

Program will crash immediately
User experience becomes poor

With exception handling:

Program handles error gracefully
Program continues execution
Provides meaningful error messages

*/
import java.util.Scanner;

public class Exceptions {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter first number: ");
            int a = sc.nextInt();

            System.out.print("Enter second number: ");
            int b = sc.nextInt();

            int result = a / b; // risky operation

            System.out.println("Result: " + result);
        }
        catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero!");
        }
        catch (Exception e) {
            System.out.println("Some other error occurred: " + e.getMessage());
        }

        System.out.println("Program continues safely...");

        sc.close();
    }
}