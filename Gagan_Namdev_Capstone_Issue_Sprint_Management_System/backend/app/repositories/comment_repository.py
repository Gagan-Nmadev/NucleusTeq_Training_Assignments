from bson import ObjectId
from app.database.connection import db


class CommentRepository:

    @staticmethod
    def create_comment(comment_data: dict):
        return db.comments.insert_one(comment_data)

    @staticmethod
    def get_comments(issue_id: str):
        return list(
            db.comments.find(
                {
                    "issue_id": issue_id
                }
            )
        )

    @staticmethod
    def get_comment_by_id(comment_id: str):
        return db.comments.find_one(
            {
                "_id": ObjectId(comment_id)
            }
        )

    @staticmethod
    def update_comment(comment_id: str, data: dict):
        return db.comments.update_one(
            {
                "_id": ObjectId(comment_id)
            },
            {
                "$set": data
            }
        )

    @staticmethod
    def delete_comment(comment_id: str):
        return db.comments.delete_one(
            {
                "_id": ObjectId(comment_id)
            }
        )