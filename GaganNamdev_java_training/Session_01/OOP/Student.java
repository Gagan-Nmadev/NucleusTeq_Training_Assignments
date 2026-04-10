class Student {
    private String name;
    private int rollNumber;

    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    // Method 1
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
    }

    // Method Overloading (same name, different parameters)
    public void displayInfo(String message) {
        System.out.println(message);
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
    }
}