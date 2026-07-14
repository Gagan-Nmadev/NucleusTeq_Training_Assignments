from app.models.project_model import ProjectModel
from app.repositories.project_repository import ProjectRepository


class ProjectService:

    @staticmethod
    def create_project(project):

        existing_project = ProjectRepository.get_project_by_name(
            project.name
        )

        if existing_project:
            return {
                "message": "Project already exists"
            }

        project_data = ProjectModel.create_project(
            name=project.name,
            description=project.description,
            members=project.members
        )

        ProjectRepository.create_project(project_data)

        return {
            "message": "Project created successfully"
        }

    @staticmethod
    def get_all_projects():

        projects = ProjectRepository.get_all_projects()

        result = []

        for project in projects:
            project["_id"] = str(project["_id"])
            result.append(project)

        return result

    @staticmethod
    def get_project_by_id(project_id: str):

        project = ProjectRepository.get_project_by_id(
            project_id
        )

        if not project:
            return {
                "message": "Project not found"
            }

        project["_id"] = str(project["_id"])

        return project

    @staticmethod
    def update_project(project_id: str, project):

        existing_project = ProjectRepository.get_project_by_id(
            project_id
        )

        if not existing_project:
            return {
                "message": "Project not found"
            }

        ProjectRepository.update_project(
            project_id,
            {
                "name": project.name,
                "description": project.description,
                "members": project.members
            }
        )

        return {
            "message": "Project updated successfully"
        }

    @staticmethod
    def delete_project(project_id: str):

        existing_project = ProjectRepository.get_project_by_id(
            project_id
        )

        if not existing_project:
            return {
                "message": "Project not found"
            }

        ProjectRepository.delete_project(project_id)

        return {
            "message": "Project deleted successfully"
        }

    @staticmethod
    def assign_members(project_id: str, data):

        project = ProjectRepository.get_project_by_id(
            project_id
        )

        if not project:
            return {
                "message": "Project not found"
            }

        ProjectRepository.assign_members(
            project_id,
            data.members
        )

        return {
            "message": "Members assigned successfully"
        }