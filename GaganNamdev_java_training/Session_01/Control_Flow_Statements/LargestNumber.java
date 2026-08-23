import java.util.Scanner;
public class LargestNumber {

    public static void main(String[] args) {

        //  Scanner object for user input
        Scanner sc = new Scanner(System.in);

        //  Taking input from user
        System.out.print("Enter first number: ");
        int num1 = sc.nextInt();

        System.out.print("Enter second number: ");
        int num2 = sc.nextInt();

        System.out.print("Enter third number: ");
        int num3 = sc.nextInt();

        int largest;
    

         //  Conditional statements to find largest number
        if (num1 >= num2 && num1 >= num3) {
            largest = num1;
        } 
        else if (num2 >= num1 && num2 >= num3) {
            largest = num2;
        } 
        else {
            largest = num3;
        }

        //  Output result
        System.out.println("Largest number is: " + largest);

        sc.close(); // good practice
    }

}
