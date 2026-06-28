# 1. Write a program that takes a number as input and handles ValueError if the input is not a valid integer.
try:
    number = int(input("Enter a number : "))
    print(f"Number: {number}")
except ValueError as e:
    print("Exception: ",e)