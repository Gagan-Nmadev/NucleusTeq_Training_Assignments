"""
Tuple Operations

Q -28
Q -29

"""


def access_tuple_elements() -> None:
    """
    Create tuple and access elements.
    """
    student = ("Gagan", 22, "Python")

    print("First Element:", student[0])
    print("Second Element:", student[1])
    print("Third Element:", student[2])


def modify_tuple() -> None:
    """
    Convert tuple into list and modify it.
    """
    student = ("Gagan", 22, "Python")

    student_list = list(student)

    student_list.append("Training")

    print("Modified List:", student_list)


def main() -> None:
    access_tuple_elements()
    print('--------------------------------')

    modify_tuple()


if __name__ == "__main__":
    main()