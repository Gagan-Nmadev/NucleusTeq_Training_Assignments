from datetime import datetime


class ProjectModel:

    @staticmethod
    def create_project(
        name,
        description,
        members
    ):

        return {
            "name": name,
            "description": description,
            "members": members,
            "created_at": datetime.utcnow()
        }