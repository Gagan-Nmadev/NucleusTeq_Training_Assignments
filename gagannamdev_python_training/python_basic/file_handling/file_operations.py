"""
File Handling Programs

This module contains:

Q-35. Write data to a file
Q-36. Count words, lines, and characters
Q-37. Append data to a file
Q-38. Copy file content
Q-39. Search a word in a file

"""

def write_file():
    file = open("student.txt", "w")
    file.write("Gagan Namdev")
    file.close()

    print("Data written successfully")


def read_file():
    file = open("student.txt", "r")
    content = file.read()
    file.close()

    lines = len(content.splitlines())
    words = len(content.split())
    characters = len(content)

    print("Lines:", lines)
    print("Words:", words)
    print("Characters:", characters)


def append_file():
    file = open("student.txt", "a")
    file.write("\nPython Training Assignment")
    file.close()

    print("Data appended successfully")


def copy_file():
    source_file = open("student.txt", "r")
    content = source_file.read()
    source_file.close()

    destination_file = open("student_copy.txt", "w")
    destination_file.write(content)
    destination_file.close()

    print("File copied successfully")


def search_word():
    word = input("Enter word to search: ")

    file = open("student.txt", "r")
    content = file.read()
    file.close()

    if word in content:
        print("Word found")
    else:
        print("Word not found")


def main():
    print("Question 35")
    write_file()

    print("\nQuestion 36")
    read_file()

    print("\nQuestion 37")
    append_file()

    print("\nQuestion 38")
    copy_file()

    print("\nQuestion 39")
    search_word()


if __name__ == "__main__":
    main()