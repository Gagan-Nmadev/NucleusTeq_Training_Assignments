from datetime import datetime


class UserModel:

    @staticmethod
    def create_user(name, email, password):

        return {
            "name": name,
            "email": email,
            "password": password,
            "created_at": datetime.utcnow()
        }