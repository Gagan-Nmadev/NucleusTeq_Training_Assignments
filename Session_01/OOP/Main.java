public class Main {
    public static void main(String[] args) {

        // Parent class object
        Student s1 = new Student("Gagan", 101);
        s1.displayInfo();
        System.out.println("----------------");

        // Overloaded method
        s1.displayInfo("Student Details:");

        System.out.println("----------------");

        // Child class object
        GraduateStudent gs = new GraduateStudent("Gagan", 101, "AI");
        gs.displayInfo(); // overridden method

        System.out.println("----------------");

        // overloaded method in child class
        gs.displayInfo("Graduate Student Details", 2026);
    }
}