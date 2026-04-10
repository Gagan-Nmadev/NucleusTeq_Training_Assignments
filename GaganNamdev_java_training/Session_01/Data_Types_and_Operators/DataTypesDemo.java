/*
 PRIMITIVE vs REFERENCE DATA TYPES

  Primitive Data Types:
- Store actual values directly
- Stored in Stack memory
- Faster & memory efficient
- Examples: int, double, char, boolean

  Reference Data Types:
- Store memory address (reference) of object
- Stored in Heap memory
- Used for objects
- Examples: String, Arrays, Classes
*/

public class DataTypesDemo {

    //  PRIMITIVE DATA TYPES
    // =========================
    public void showPrimitiveTypes() {

        int age = 21;              // stores actual integer value
        double salary = 45000.75;  // stores decimal value
        char grade = 'A';         // stores single character
        boolean isActive = true;  // stores true/false

        System.out.println("----Primitive Data Types----");
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
        System.out.println("Grade: " + grade);
        System.out.println("Is Active: " + isActive);
    }

  
    // REFERENCE DATA TYPES
    // =========================
    public void showReferenceTypes() {

        String studentName = "Rahul";  // String object (reference type)

        int[] marks = {85, 90, 78};    // Array is reference type

        System.out.println("\n---- Reference Data Types ----");
        System.out.println("Student Name: " + studentName);

        System.out.println("Marks:");
        System.out.println("Subject 1: " + marks[0]);
        System.out.println("Subject 2: " + marks[1]);
        System.out.println("Subject 3: " + marks[2]);
    }

    //  MAIN METHOD
    // =========================
    public static void main(String[] args) {

        DataTypesDemo demo = new DataTypesDemo();

        demo.showPrimitiveTypes();
        demo.showReferenceTypes();
    }
}