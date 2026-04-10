class GraduateStudent extends Student {

    private String specialization;

    public GraduateStudent(String name, int rollNumber, String specialization) {
        super(name, rollNumber);
        this.specialization = specialization;
    }

    // Overriding parent method
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Specialization: " + specialization);
    }

    // Overloading again in child class
    public void displayInfo(String message, int year) {
        System.out.println(message);
        System.out.println("Year: " + year);
        System.out.println("Specialization: " + specialization);
    }
}