"""
Module Examples

This module demonstrates:
1. Math Module
2. Random Module
3. Custom Module
"""

import math   # module
import random  # module
from custom_module import add_numbers, multiply_numbers  # module


def math_module_demo() -> None:
    """
    Demonstrates math module functions.
    """
    number: int = int(input("Enter a number: "))

    print(f"Square Root: {math.sqrt(number)}")
    print(f"Power (number^2): {math.pow(number, 2)}")
    print(f"Factorial: {math.factorial(number)}")


def random_module_demo() -> None:
    """
    Demonstrates random module functions.
    """
    print("\nRandom Numbers")

    for _ in range(5):
        print(random.randint(1, 100))


def custom_module_demo() -> None:
    """
    Demonstrates custom module import.
    """
    first_number: int = 10
    second_number: int = 20

    print(
        f"Addition: "
        f"{add_numbers(first_number, second_number)}"
    )

    print(
        f"Multiplication: "
        f"{multiply_numbers(first_number, second_number)}"
    )


def main() -> None:
    """
    Executes all module examples.
    """

    print("Question 22")
    math_module_demo()

    print("-------------------------------------------")

    print("Question 23")
    random_module_demo()

    print("-------------------------------------------")

    print("Question 24")
    custom_module_demo()


if __name__ == "__main__":
    main()