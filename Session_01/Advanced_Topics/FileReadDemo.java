import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReadDemo {

    public static void main(String[] args) {

        try {
            // Create file object (give your file path here)
            File file = new File("input.txt");

            // Scanner to read file
            Scanner sc = new Scanner(file);

            System.out.println("File content:");

            // Read file line by line
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }

            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: File not found!");
        }

        System.out.println("Program ended safely.");
    }
}