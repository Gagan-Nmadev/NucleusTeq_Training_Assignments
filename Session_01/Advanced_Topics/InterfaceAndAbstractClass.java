// package NucleusTeq_Training_Assignments.GaganNamdev_java_training.Session_01.Advanced_Topics;

/*
Advanced Topics:
 1. Explain the concept of interfaces and abstract classes with examples.

Abstract Class:
- Declared using "abstract" keyword
- Can have both abstract (no body) and non-abstract methods
- Can have constructors and instance variables
- Cannot be instantiated (no object creation)
- Used when classes are related and share common behavior

-----------------------------------------------------
Interface:
- A blueprint of a class
- Contains abstract methods by default
- Variables are public, static, and final
- Achieves multiple inheritance using "implements"
- Used to define a contract (what a class should do)

-----------------------------------------------------
Difference:
- Abstract class → partial abstraction (methods + implementation)
- Interface → full abstraction (only method definitions)

- Abstract class → extends (single inheritance)
- Interface → implements (multiple inheritance allowed)

-----------------------------------------------------

Examples of Abstract Class and Interface are menttioned below:
*/

// Abstract class
abstract class Vehicle {

    abstract void start(); // abstract method

    void fuelType() { // normal method
        System.out.println("Uses fuel or electricity");
    }
}

// Interface
interface Electric {

    void charge(); // abstract method
}

// Child class
class Tesla extends Vehicle implements Electric {

    // implementing abstract method of Vehicle
    public void start() {
        System.out.println("Tesla starts silently with push button");
    }

    // implementing interface method
    public void charge() {
        System.out.println("Tesla is charging using supercharger");
    }
}

// Another child class
class Bike extends Vehicle {

    // implementing abstract method
    public void start() {
        System.out.println("Bike starts with kick or self-start");
    }
}

public class InterfaceAndAbstractClass {
    public static void main(String[] args) {

        Tesla t = new Tesla();
        t.start();
        t.fuelType();
        t.charge();

        System.out.println("----------------");

        Bike b = new Bike();
        b.start();
        b.fuelType();
    }
}