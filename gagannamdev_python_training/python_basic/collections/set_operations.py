"""
Set Operations
Q -30
Q -31

"""


def set_operations() -> None:
    """
    Union, Intersection and Difference.
    """
    set_a = {1, 2, 3, 4, 5}
    set_b = {4, 5, 6, 7, 8}

    print("Union:", set_a.union(set_b))
    print("Intersection:", set_a.intersection(set_b))
    print("Difference:", set_a.difference(set_b))


def remove_duplicates() -> None:
    """
    Remove duplicates from list.
    """
    numbers = [1, 2, 2, 3, 4, 4, 5]

    unique_numbers = list(set(numbers))

    print("Original List:", numbers)
    print("Without Duplicates:", unique_numbers)


def main() -> None:
    set_operations()
    print("--------------------------------")

    remove_duplicates()


if __name__ == "__main__":
    main()