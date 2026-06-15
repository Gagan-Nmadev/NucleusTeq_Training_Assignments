"""
Functions Programs

This module contains:
1. Square of a number
2. Palindrome check (number and string)
3. Maximum number from a list
4. Function with default parameters
"""


def calculate_square(number: int) -> int:
    """
    Returns the square of a number.
    """
    return number * number


def check_palindrome(value: str) -> bool:
    """
    Checks whether a string or number is palindrome.
    """
    text = str(value)
    return text == text[::-1]


def find_maximum(numbers: list[int]) -> int:
    """
    Returns the maximum value from a list.
    """
    return max(numbers)


def greet_user(name: str = "Guest") -> str:
    """
    Demonstrates default parameters.
    """
    return f"Welcome, {name}!"


def main() -> None:
    """
    Executes all function programs.
    """

    # Question 17
    number = int(input("Enter a number to find square: "))
    print(f"Square = {calculate_square(number)}")

    print("--------------------------------------------")

    # Question 18
    value = input("Enter a string or number: ")

    if check_palindrome(value):
        print(f"{value} is a Palindrome.")
    else:
        print(f"{value} is not a Palindrome.")

    print("--------------------------------------------")

    # Question 19
    number_list = [10, 45, 78, 23, 99, 12]
    print(f"List: {number_list}")
    print(f"Maximum Number: {find_maximum(number_list)}")

    print("--------------------------------------------")
    # Question 20
    print(greet_user())
    print(greet_user("Gagan"))


if __name__ == "__main__":
    main()