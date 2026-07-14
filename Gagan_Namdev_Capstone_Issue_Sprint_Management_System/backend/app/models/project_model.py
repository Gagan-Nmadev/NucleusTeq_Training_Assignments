from datetime import datetime
import uuid


class ProjectModel:

    @staticmethod
    def create_project(
        name,
        description,
        members
    ):

        project_key = (
            "PROJ_" +
            uuid.uuid4().hex[:6].upper()
        )

        return {
            "project_key": project_key,
            "name": name,
            "description": description,
            "members": members,
            "created_at": datetime.utcnow()
        }