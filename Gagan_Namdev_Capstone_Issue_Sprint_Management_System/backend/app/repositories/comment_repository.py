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