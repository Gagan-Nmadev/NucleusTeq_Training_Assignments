"""
Introduction to Python Programs

This module contains basic Python programs:
1. Print welcome message
2. Display Python version
3. Take user input and display formatted output
"""

import sys


def print_welcome_message() -> None:
    """
    Prints a welcome message.
    """
    print("Welcome to Python Training")


def display_python_version() -> None:
    """
    Displays the current Python version.
    """
    print(f"Python Version: {sys.version}")


def get_user_details() -> None:
    """
    Takes user name and age as input and displays a formatted message.
    """
    user_name: str = input("Enter your name: ")
    user_age: int = int(input("Enter your age: "))

    print(
        f"Hello {user_name}! "
        f"You are {user_age} years old. "
        f"Welcome to Python Training."
    )


def main() -> None:
    """
    Main function to execute all introduction programs.
    """
    print_welcome_message()
    

    display_python_version()
    

    get_user_details()


if __name__ == "__main__":
    main()