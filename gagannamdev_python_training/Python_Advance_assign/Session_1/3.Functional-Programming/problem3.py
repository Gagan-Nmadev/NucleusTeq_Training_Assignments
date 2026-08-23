# Q3. Use filter() to extract even numbers from a list.

numbers = [1, 52, 3, 55, 5, 64, 7, 28, 10]

evens = filter(lambda x: x % 2 == 0, numbers)

try:
    while True:
        print(next(evens))
except StopIteration:
    print("No more even numbers.")