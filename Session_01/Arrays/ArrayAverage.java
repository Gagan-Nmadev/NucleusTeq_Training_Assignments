// package NucleusTeq_Training_Assignments.GaganNamdev_java_training.Session_01.Arrays;

import java.util.Scanner;

public class ArrayAverage {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //  size of array input
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n]; //  array declaration

        int sum = 0;

        // input array elements
        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum = sum + arr[i]; // add each element to sum
        }

        // 🔹 calculate average
        double average = (double) sum / n;

        // 🔹 output result
        System.out.println("Average of array elements is: " + average);

        sc.close();
    }
}

