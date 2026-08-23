"""
Loop Programs

This module contains:
1. Print numbers from 1 to 100
2. Multiplication table
3. Factorial of a number
4. Reverse a number
5. Prime number check
"""


def print_numbers() -> None:
    """
    Prints numbers from 1 to 100.
    """
    for number in range(1, 101):
        print(number, end=" ")
    print()


def multiplication_table() -> None:
    """
    Prints multiplication table of a number.
    """
    number: int = int(input("Enter a number: "))

    print(f"\nMultiplication Table of {number}")

    for count in range(1, 11):
        print(f"{number} x {count} = {number * count}")


def factorial_number() -> None:
    """
    Calculates factorial of a number.
    """
    number: int = int(input("Enter a number: "))

    factorial: int = 1

    for count in range(1, number + 1):
        factorial *= count

    print(f"Factorial of {number} = {factorial}")


def reverse_number() -> None:
    """
    Reverses a number using loop.
    """
    number: int = int(input("Enter a number: "))

    reversed_number: int = 0
    original_number: int = number

    while number > 0:
        digit = number % 10
        reversed_number = reversed_number * 10 + digit
        number //= 10

    print(
        f"Reverse of {original_number} = "
        f"{reversed_number}"
    )


def check_prime_number() -> None:
    """
    Checks whether a number is prime.
    """
    number: int = int(input("Enter a number: "))

    if number <= 1:
        print(f"{number} is not a Prime Number.")
        return

    for divisor in range(2, number):
        if number % divisor == 0:
            print(f"{number} is not a Prime Number.")
            return

    print(f"{number} is a Prime Number.")


def main() -> None:
    """
    Executes all loop programs.
    """
    print("\nQuestion 12")
    print_numbers()

    print("\n" + "-" * 50) # this is for line break.

    print("Question 13")
    multiplication_table()

    print("\n" + "-" * 50)  # this is for line break.


    print("Question 14")
    factorial_number()

    print("\n" + "-" * 50) # this is for line break.


    print("Question 15")
    reverse_number()

    print("\n" + "-" * 50)

    print("Question 16")
    check_prime_number()


if __name__ == "__main__":
    main()