"""
Variables and Data Types Programs

This module contains:
1. Different data types
2. Number swapping
3. Arithmetic operations
"""


def display_data_types() -> None:
    """
    Creates variables of different data types
    and displays their types.
    """
    student_age: int = 22
    student_percentage: float = 85.5
    student_name: str = "Gagan"
    is_placed: bool = True

    print("Integer:", student_age, type(student_age))
    print("Float:", student_percentage, type(student_percentage))
    print("String:", student_name, type(student_name))
    print("Boolean:", is_placed, type(is_placed))


def swap_numbers() -> None:
    """
    Swaps two numbers.
    """
    first_number: int = int(input("Enter first number: "))
    second_number: int = int(input("Enter second number: "))

    print("\nBefore Swapping")
    print("First Number:", first_number)
    print("Second Number:", second_number)

    first_number, second_number = second_number, first_number

    print("\nAfter Swapping")
    print("First Number:", first_number)
    print("Second Number:", second_number)


def arithmetic_operations() -> None:
    """
    Performs basic arithmetic operations.
    """
    first_number: float = float(input("Enter first number: "))
    second_number: float = float(input("Enter second number: "))

    print("\nArithmetic Operations")
    print(f"Sum = {first_number + second_number}")
    print(f"Difference = {first_number - second_number}")
    print(f"Multiplication = {first_number * second_number}")

    if second_number != 0:
        print(f"Division = {first_number / second_number}")
    else:
        print("Division by zero is not allowed.")


def main() -> None:
    """
    Main function.
    """
    print("Question 4")
    display_data_types()

    print("\n" + "-" * 50)

    print("Question 5")
    swap_numbers()

    print("\n" + "-" * 50)

    print("Question 6")
    arithmetic_operations()


if __name__ == "__main__":
    main()