from bson import ObjectId
from app.database.connection import db


class SprintRepository:

    @staticmethod
    def create_sprint(sprint_data: dict):
        return db.sprints.insert_one(sprint_data)

    @staticmethod
    def get_all_sprints():
        return list(db.sprints.find())

    @staticmethod
    def get_sprint_by_id(sprint_id: str):

        if not ObjectId.is_valid(sprint_id):
            return None

        return db.sprints.find_one(
            {
                "_id": ObjectId(sprint_id)
            }
        )

    @staticmethod
    def update_sprint(sprint_id: str, data: dict):

        if not ObjectId.is_valid(sprint_id):
            return None

        return db.sprints.update_one(
            {
                "_id": ObjectId(sprint_id)
            },
            {
                "$set": data
            }
        )

    @staticmethod
    def delete_sprint(sprint_id: str):

        if not ObjectId.is_valid(sprint_id):
            return None

        return db.sprints.delete_one(
            {
                "_id": ObjectId(sprint_id)
            }
        )

    @staticmethod
    def get_by_project(project_id: str):
        return list(
            db.sprints.find(
                {
                    "project_id": project_id
                }
            )
        )

    @staticmethod
    def get_by_status(status: str):
        return list(
            db.sprints.find(
                {
                    "status": status
                }
            )
        )