import java.util.Scanner;

public class AnagramCheck {

    public static boolean isAnagram(String str1, String str2) {
        // logic will be added in next commit
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String s1 = sc.nextLine();

        System.out.print("Enter second string: ");
        String s2 = sc.nextLine();

        boolean result = isAnagram(s1, s2);

        if (result) {
            System.out.println("Strings are Anagrams");
        } else {
            System.out.println("Strings are NOT Anagrams");
        }

        sc.close();
    }
}