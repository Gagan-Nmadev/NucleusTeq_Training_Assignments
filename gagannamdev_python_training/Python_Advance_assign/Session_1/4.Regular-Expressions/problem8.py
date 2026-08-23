import re

min_len = 10
max_len = 15

password = "gaganwsd!9"

contains_special = re.search(r"\W", password)
contains_space = re.search(r"\s", password)
contains_digit = re.search(r"\d", password)

if (
    not contains_space
    and contains_special
    and contains_digit
    and min_len <= len(password) <= max_len
):
    print("Valid Password")
else:
    print("Invalid Password")