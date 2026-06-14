"""
Operators and Conditional Statements

This module contains:
1. Even or Odd Number Check
2. Positive, Negative, or Zero Check
3. Largest of Three Numbers
4. Grade Calculator
5. Leap Year Checker
"""


def check_even_odd() -> None:
    """
    Checks whether a number is even or odd.
    """
    number: int = int(input("Enter a number: "))

    if number % 2 == 0:
        print(f"{number} is Even.")
    else:
        print(f"{number} is Odd.")


def check_number_sign() -> None:
    """
    Checks whether a number is positive, negative, or zero.
    """
    number: float = float(input("Enter a number: "))

    if number > 0:
        print("The number is Positive.")
    elif number < 0:
        print("The number is Negative.")
    else:
        print("The number is Zero.")


def find_largest_number() -> None:
    """
    Finds the largest among three numbers.
    """
    first_number: float = float(input("Enter first number: "))
    second_number: float = float(input("Enter second number: "))
    third_number: float = float(input("Enter third number: "))

    largest_number: float = max(
        first_number,
        second_number,
        third_number
    )

    print(f"Largest Number: {largest_number}")


def calculate_grade() -> None:
    """
    Calculates grade based on marks.
    """
    marks: float = float(input("Enter marks (0-100): "))

    if marks >= 80:
        grade = "A"
    elif marks >= 60:
        grade = "B"
    elif marks >= 40:
        grade = "C"
    else:
        grade = "Fail"

    print(f"Grade: {grade}")


def check_leap_year() -> None:
    """
    Checks whether a year is a leap year.
    """
    year: int = int(input("Enter a year: "))

    if (year % 400 == 0) or (
        year % 4 == 0 and year % 100 != 0
    ):
        print(f"{year} is a Leap Year.")
    else:
        print(f"{year} is not a Leap Year.")


def main() -> None:
    """
    Executes all conditional programs.
    """
    print("\nQuestion 7")
    check_even_odd()

    print("\n" + "-" * 50)

    print("Question 8")
    check_number_sign()

    print("\n" + "-" * 50)

    print("Question 9")
    find_largest_number()

    print("\n" + "-" * 50)

    print("Question 10")
    calculate_grade()

    print("\n" + "-" * 50)

    print("Question 11")
    check_leap_year()


if __name__ == "__main__":
    main()