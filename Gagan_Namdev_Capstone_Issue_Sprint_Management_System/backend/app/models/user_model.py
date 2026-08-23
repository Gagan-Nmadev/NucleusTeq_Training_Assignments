from datetime import datetime


class UserModel:

    @staticmethod
    def create_user(name, email, password, role):

        return {
            "name": name,
            "email": email,
            "password": password,
            "role": role,
            "created_at": datetime.utcnow()
        }