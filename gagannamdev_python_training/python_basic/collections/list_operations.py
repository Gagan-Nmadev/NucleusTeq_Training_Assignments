"""
List Operations

Q -25
Q -26
Q -27

"""


def list_summary() -> None:
    """
    Find sum, max, sort list and remove duplicates.
    """
    numbers = [10, 5, 20, 15, 10, 25, 30, 5, 40, 50]

    print("Original List:", numbers)
    print("Sum:", sum(numbers))
    print("Maximum:", max(numbers))
    print("Sorted List:", sorted(numbers))
    print("Without Duplicates:", list(set(numbers)))


def count_even_odd() -> None:
    """
    Count even and odd numbers.
    """
    numbers = [10, 15, 22, 33, 40, 55, 60]

    even_count = 0
    odd_count = 0

    for number in numbers:
        if number % 2 == 0:
            even_count += 1
        else:
            odd_count += 1

    print("Even Numbers:", even_count)
    print("Odd Numbers:", odd_count)


def reverse_list() -> None:
    """
    Reverse list without reverse().
    """
    numbers = [1, 2, 3, 4, 5]

    reversed_list = numbers[::-1]

    print("Original List:", numbers)
    print("Reversed List:", reversed_list)


def main() -> None:
    list_summary()
    print("------------------------------------------")

    count_even_odd()
    print("------------------------------------------")

    reverse_list()


if __name__ == "__main__":
    main()