from bson import ObjectId
from app.database.connection import db


class ProjectRepository:

    @staticmethod
    def create_project(project_data: dict):
        return db.projects.insert_one(project_data)

    @staticmethod
    def get_project_by_name(name: str):
        return db.projects.find_one({"name": name})

    @staticmethod
    def get_all_projects():
        return list(db.projects.find())

    @staticmethod
    def get_project_by_id(project_id: str):
        return db.projects.find_one(
            {"_id": ObjectId(project_id)}
        )