public class OperatorsDemo {
    public static void main(String[] args) {

        // =========================
        // 🔹 ARITHMETIC OPERATORS
        // =========================
        int a = 10;
        int b = 5;

        System.out.println("===== Arithmetic Operators =====");
        System.out.println("Addition: " + (a + b));        // 15
        System.out.println("Subtraction: " + (a - b));     // 5
        System.out.println("Multiplication: " + (a * b));  // 50
        System.out.println("Division: " + (a / b));        // 2
        System.out.println("Modulus: " + (a % b));         // 0


       //     RELATIONAL OPERATORS
        // =========================
        System.out.println("\n===== Relational Operators =====");

        System.out.println("a == b : " + (a == b));  // false
        System.out.println("a != b : " + (a != b));  // true
        System.out.println("a > b  : " + (a > b));   // true
        System.out.println("a < b  : " + (a < b));   // false
        System.out.println("a >= b : " + (a >= b));  // true
        System.out.println("a <= b : " + (a <= b));  // false


        //     LOGICAL OPERATORS
        // =========================
        System.out.println("\n===== Logical Operators =====");

        boolean condition1 = (a > b);   // true
        boolean condition2 = (a < b);   // false

        System.out.println("condition1 && condition2 : " + (condition1 && condition2)); // false
        System.out.println("condition1 || condition2 : " + (condition1 || condition2)); // true
        System.out.println("!condition1 : " + (!condition1)); // false
    }
}


    

