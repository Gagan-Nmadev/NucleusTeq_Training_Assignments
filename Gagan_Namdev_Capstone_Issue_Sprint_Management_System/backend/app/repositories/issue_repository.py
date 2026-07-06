from app.database.connection import db


class IssueRepository:

    @staticmethod
    def create_issue(issue_data: dict):
        return db.issues.insert_one(issue_data)