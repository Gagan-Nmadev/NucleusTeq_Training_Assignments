from app.database.connection import db
from bson import ObjectId


class UserRepository:

    @staticmethod
    def get_user_by_email(email: str):
        return db.users.find_one({"email": email})

    @staticmethod
    def create_user(user_data: dict):
        return db.users.insert_one(user_data)

    @staticmethod
    def delete_user(user_id: str):
        return db.users.delete_one(
            {"_id": ObjectId(user_id)}
        )