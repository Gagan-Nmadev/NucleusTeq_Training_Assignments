from app.models.sprint_model import SprintModel
from app.repositories.sprint_repository import SprintRepository
from app.repositories.project_repository import ProjectRepository
from app.repositories.issue_repository import IssueRepository


class SprintService:

    @staticmethod
    def create_sprint(sprint):

        project = ProjectRepository.get_project_by_id(
            sprint.project_id
        )

        if not project:
            return {
                "message": "Project not found"
            }

        sprint_data = SprintModel.create_sprint(
            name=sprint.name,
            project_id=sprint.project_id,
            start_date=sprint.start_date,
            end_date=sprint.end_date
        )

        SprintRepository.create_sprint(sprint_data)

        return {
            "message": "Sprint created successfully"
        }

    @staticmethod
    def get_all_sprints():

        sprints = SprintRepository.get_all_sprints()

        result = []

        for sprint in sprints:
            sprint["_id"] = str(sprint["_id"])
            result.append(sprint)

        return result

    @staticmethod
    def get_sprint_by_id(sprint_id: str):

        sprint = SprintRepository.get_sprint_by_id(
            sprint_id
        )

        if not sprint:
            return {
                "message": "Sprint not found"
            }

        sprint["_id"] = str(sprint["_id"])

        return sprint

    @staticmethod
    def add_issue(sprint_id: str, data):

        sprint = SprintRepository.get_sprint_by_id(
            sprint_id
        )

        if not sprint:
            return {
                "message": "Sprint not found"
            }

        issue = IssueRepository.get_issue_by_id(
            data.issue_id
        )

        if not issue:
            return {
                "message": "Issue not found"
            }

        if data.issue_id in sprint["issues"]:
            return {
                "message": "Issue already added"
            }

        sprint["issues"].append(data.issue_id)

        SprintRepository.update_sprint(
            sprint_id,
            {
                "issues": sprint["issues"]
            }
        )

        return {
            "message": "Issue added successfully"
        }

    @staticmethod
    def remove_issue(sprint_id: str, data):

        sprint = SprintRepository.get_sprint_by_id(
            sprint_id
        )

        if not sprint:
            return {
                "message": "Sprint not found"
            }

        if data.issue_id not in sprint["issues"]:
            return {
                "message": "Issue not found in sprint"
            }

        sprint["issues"].remove(data.issue_id)

        SprintRepository.update_sprint(
            sprint_id,
            {
                "issues": sprint["issues"]
            }
        )

        return {
            "message": "Issue removed successfully"
        }