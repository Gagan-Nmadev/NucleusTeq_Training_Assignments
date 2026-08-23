public class SumOfEvenNumbers {

     public static void main(String[] args) {

        //  starting number
        int i = 1;

        //  variable to store sum of even numbers
        int sum = 0;
        while (i <= 10) {

            // 🔹 check even number
            if (i % 2 == 0) {
                sum = sum + i; // add even number to sum
            }

            i++; // increment counter
        }
          // final output
           System.out.println("Sum of even numbers from 1 to 10 is: " + sum);
    }
}

