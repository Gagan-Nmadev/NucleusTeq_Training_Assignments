from bson import ObjectId
from app.database.connection import db


class IssueRepository:

    @staticmethod
    def create_issue(issue_data: dict):
        return db.issues.insert_one(issue_data)

    @staticmethod
    def get_issue_by_id(issue_id: str):
        return db.issues.find_one(
            {
                "_id": ObjectId(issue_id)
            }
        )

    @staticmethod
    def update_issue(issue_id: str, data: dict):
        return db.issues.update_one(
            {
                "_id": ObjectId(issue_id)
            },
            {
                "$set": data
            }
        )

    @staticmethod
    def get_by_status(status: str):
        return list(
            db.issues.find(
                {
                    "status": status
                }
            )
        )

    @staticmethod
    def get_by_priority(priority: str):
        return list(
            db.issues.find(
                {
                    "priority": priority
                }
            )
        )

    @staticmethod
    def get_by_assignee(assignee: str):
        return list(
            db.issues.find(
                {
                    "assignee": assignee
                }
            )
        )

    @staticmethod
    def get_by_project(project_id: str):
        return list(
            db.issues.find(
                {
                    "project_id": project_id
                }
            )
        )