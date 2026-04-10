import java.util.Scanner;

public class LinearSearch {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //  array size input
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        //  array elements input
        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
         }

        //  element to search
        System.out.print("Enter element to search: ");
        int key = sc.nextInt();

        int index = -1; // -1 means not found

        // LINEAR SEARCH LOGIC
        // =========================
        for (int i = 0; i < n; i++) {

            if (arr[i] == key) {
                index = i;
                break; // stop when found
            }
        }

    
}
