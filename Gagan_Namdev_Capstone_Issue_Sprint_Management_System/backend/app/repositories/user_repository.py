from app.database.connection import db


class UserRepository:

    @staticmethod
    def get_user_by_email(email: str):
        return db.users.find_one({"email": email})

    @staticmethod
    def create_user(user_data: dict):
        return db.users.insert_one(user_data)