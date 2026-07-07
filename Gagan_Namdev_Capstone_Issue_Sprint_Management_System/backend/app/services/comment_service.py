from app.models.comment_model import CommentModel
from app.repositories.comment_repository import CommentRepository
from app.repositories.issue_repository import IssueRepository


class CommentService:

    @staticmethod
    def create_comment(comment, current_user):

        issue = IssueRepository.get_issue_by_id(
            comment.issue_id
        )

        if not issue:
            return {
                "message": "Issue not found"
            }

        comment_data = CommentModel.create_comment(
            issue_id=comment.issue_id,
            user_email=current_user["email"],
            comment=comment.comment
        )

        CommentRepository.create_comment(comment_data)

        return {
            "message": "Comment added successfully"
        }

    @staticmethod
    def get_comments(issue_id: str):

        issue = IssueRepository.get_issue_by_id(issue_id)

        if not issue:
            return {
                "message": "Issue not found"
            }

        comments = CommentRepository.get_comments(issue_id)

        result = []

        for comment in comments:
            comment["_id"] = str(comment["_id"])
            result.append(comment)

        return result