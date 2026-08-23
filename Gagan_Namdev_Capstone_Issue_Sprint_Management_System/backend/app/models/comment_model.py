from datetime import datetime


class CommentModel:

    @staticmethod
    def create_comment(
        issue_id,
        user_email,
        comment
    ):
        return {
            "issue_id": issue_id,
            "user_email": user_email,
            "comment": comment,
            "created_at": datetime.utcnow()
        }