import re

# Q1. Write a program to extract all numbers from a given string using regular expressions.

print("Q1. Write a program to extract all numbers from a given string using regular expressions.")
text = "todays 20th day of the month of jun and year 2026"

answer1 = re.findall(r"\d+", text)
print(answer1)