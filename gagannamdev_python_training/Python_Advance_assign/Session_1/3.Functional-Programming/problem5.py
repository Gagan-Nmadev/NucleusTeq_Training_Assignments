# Q5. Write a recursive function to calculate factorial.

print("Q5. Write a recursive function to calculate factorial.")


def fact(num):
    if num == 0 or num == 1:
        return 1
    return num * fact(num - 1)


num = int(input("Enter a number: "))

if num < 0:
    print("Factorial is not defined for negative numbers.")
else:
    print(f"Factorial of {num} = {fact(num)}")

print("-" * 50)