"""
Dictionary Operations

Q -32
Q -33
Q -34

"""


def student_dictionary() -> None:
    """
    Create and access dictionary values.
    """
    student = {
        "name": "Gagan",
        "age": 22,
        "course": "Python"
    }

    print("Name:", student["name"])
    print("Age:", student["age"])
    print("Course:", student["course"])


def character_frequency() -> None:
    """
    Count frequency of characters.
    """
    text = input("Enter a string: ")

    frequency = {}

    for character in text:
        frequency[character] = (
            frequency.get(character, 0) + 1
        )

    print(frequency)


def merge_dictionaries() -> None:
    """
    Merge two dictionaries.
    """
    dictionary_one = {
        "name": "Gagan"
    }

    dictionary_two = {
        "course": "Python"
    }

    merged_dictionary = (
        dictionary_one | dictionary_two
    )

    print(merged_dictionary)


def main() -> None:
    student_dictionary()
    print("------------------------------")

    character_frequency()
    print("------------------------------")

    merge_dictionaries()


if __name__ == "__main__":
    main()