class Car:

    def __init__(self, brand, model):
        self.brand = brand
        self.model = model

    def display_car(self):
        print("Brand:", self.brand)
        print("Model:", self.model)


car = Car("Toyota", "Fortuner")

car.display_car()
