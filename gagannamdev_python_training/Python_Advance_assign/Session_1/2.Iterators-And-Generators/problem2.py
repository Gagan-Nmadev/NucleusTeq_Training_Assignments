print("Q2. Write a custom iterator class that returns numbers from 1 to N.")


class MyIter:

    def __init__(self, limit):
        self.limit = limit
        self.counter = 1

    def __iter__(self):
        return self

    def __next__(self):
        if self.counter <= self.limit:
            value = self.counter
            self.counter += 1
            return value
        else:
            raise StopIteration


# Take input from user
number = int(input("Enter the value of N: "))

iter1 = MyIter(number)

print(f"Numbers from 1 to {number} are:")

for value in iter1:
    print(value)