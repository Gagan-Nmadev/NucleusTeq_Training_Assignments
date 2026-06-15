class Student:

    def __init__(self, name, age, course):
        self.name = name
        self.age = age
        self.course = course

    def display_details(self):
        print("Name:", self.name)
        print("Age:", self.age)
        print("Course:", self.course)


student = Student("Gagan", 22, "Python")

student.display_details()