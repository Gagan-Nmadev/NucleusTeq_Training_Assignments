from datetime import datetime, date

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

        if sprint.start_date > sprint.end_date:
            return {
                "message": "End date must be after start date"
            }

        if sprint.start_date < date.today():
            return {
                "message": "Start date cannot be in the past"
            }

        sprint_data = SprintModel.create_sprint(
            name=sprint.name,
            description=sprint.description,
            goal=sprint.goal,
            project_id=sprint.project_id,
            start_date=sprint.start_date,
            end_date=sprint.end_date
        )

        SprintRepository.create_sprint(
            sprint_data
        )

        return {
            "message": "Sprint created successfully"
        }

    @staticmethod
    def get_all_sprints():

        sprints = SprintRepository.get_all_sprints()

        for sprint in sprints:

            sprint["_id"] = str(
                sprint["_id"]
            )

            project = ProjectRepository.get_project_by_id(
                sprint["project_id"]
            )

            sprint["project_name"] = (
                project["name"]
                if project else
                "N/A"
            )

            sprint["issue_count"] = len(
                sprint.get("issues", [])
            )

        return sprints

    @staticmethod
    def get_sprint_by_id(
        sprint_id: str
    ):

        sprint = SprintRepository.get_sprint_by_id(
            sprint_id
        )

        if not sprint:
            return {
                "message": "Sprint not found"
            }

        sprint["_id"] = str(
            sprint["_id"]
        )

        project = ProjectRepository.get_project_by_id(
            sprint["project_id"]
        )

        sprint["project_name"] = (
            project["name"]
            if project else
            "N/A"
        )

        sprint["issue_count"] = len(
            sprint.get("issues", [])
        )

        return sprint

    @staticmethod
    def update_sprint(
        sprint_id: str,
        sprint_data
    ):

        sprint = SprintRepository.get_sprint_by_id(
            sprint_id
        )

        if not sprint:
            return {
                "message": "Sprint not found"
            }

        project = ProjectRepository.get_project_by_id(
            sprint_data.project_id
        )

        if not project:
            return {
                "message": "Project not found"
            }

        if sprint_data.start_date > sprint_data.end_date:
            return {
                "message": "End date must be after start date"
            }

        SprintRepository.update_sprint(

            sprint_id,

            {
                "name": sprint_data.name,
                "description": sprint_data.description,
                "goal": sprint_data.goal,
                "project_id": sprint_data.project_id,
                "start_date": sprint_data.start_date,
                "end_date": sprint_data.end_date,
                "updated_at": datetime.utcnow()
            }

        )

        return {
            "message": "Sprint updated successfully"
        }

    @staticmethod
    def delete_sprint(
        sprint_id: str
    ):

        sprint = SprintRepository.get_sprint_by_id(
            sprint_id
        )

        if not sprint:
            return {
                "message": "Sprint not found"
            }

        SprintRepository.delete_sprint(
            sprint_id
        )

        return {
            "message": "Sprint deleted successfully"
        }
    @staticmethod
    def delete_sprint(
        sprint_id: str
    ):

        sprint = SprintRepository.get_sprint_by_id(
            sprint_id
        )

        if not sprint:
            return {
                "message": "Sprint not found"
            }

        if sprint.get("status") == "ACTIVE":
            return {
                "message": "Active sprint cannot be deleted"
            }

        SprintRepository.delete_sprint(
            sprint_id
        )

        return {
            "message": "Sprint deleted successfully"
        }

    @staticmethod
    def start_sprint(
        sprint_id: str
    ):

        sprint = SprintRepository.get_sprint_by_id(
            sprint_id
        )

        if not sprint:
            return {
                "message": "Sprint not found"
            }

        if sprint.get("status") == "ACTIVE":
            return {
                "message": "Sprint is already active"
            }

        if sprint.get("status") == "COMPLETED":
            return {
                "message": "Completed sprint cannot be started"
            }

        SprintRepository.update_sprint(
            sprint_id,
            {
                "status": "ACTIVE",
                "updated_at": datetime.utcnow()
            }
        )

        return {
            "message": "Sprint started successfully"
        }

    @staticmethod
    def complete_sprint(
        sprint_id: str
    ):

        sprint = SprintRepository.get_sprint_by_id(
            sprint_id
        )

        if not sprint:
            return {
                "message": "Sprint not found"
            }

        if sprint.get("status") != "ACTIVE":
            return {
                "message": "Only active sprint can be completed"
            }

        SprintRepository.update_sprint(
            sprint_id,
            {
                "status": "COMPLETED",
                "completed_at": datetime.utcnow(),
                "updated_at": datetime.utcnow()
            }
        )

        return {
            "message": "Sprint completed successfully"
        }

    @staticmethod
    def add_issue(
        sprint_id: str,
        issue
    ):

        sprint = SprintRepository.get_sprint_by_id(
            sprint_id
        )

        if not sprint:
            return {
                "message": "Sprint not found"
            }

        issue_data = IssueRepository.get_issue_by_id(
            issue.issue_id
        )

        if not issue_data:
            return {
                "message": "Issue not found"
            }

        if issue_data["project_id"] != sprint["project_id"]:
            return {
                "message": "Issue belongs to another project"
            }

        issues = sprint.get(
            "issues",
            []
        )

        if issue.issue_id in issues:
            return {
                "message": "Issue already added"
            }

        issues.append(
            issue.issue_id
        )

        SprintRepository.update_sprint(
            sprint_id,
            {
                "issues": issues,
                "updated_at": datetime.utcnow()
            }
        )

        return {
            "message": "Issue added to sprint successfully"
        }
    @staticmethod
    def add_issue(
        sprint_id: str,
        issue
    ):

        sprint = SprintRepository.get_sprint_by_id(
            sprint_id
        )

        if not sprint:
            return {
                "message": "Sprint not found"
            }

        issue_data = IssueRepository.get_issue_by_id(
            issue.issue_id
        )

        if not issue_data:
            return {
                "message": "Issue not found"
            }

        if issue_data["project_id"] != sprint["project_id"]:
            return {
                "message": "Issue belongs to another project"
            }

        if issue_data.get("status") == "DONE":
            return {
                "message": "Completed issue cannot be added"
            }

        issues = sprint.get(
            "issues",
            []
        )

        if issue.issue_id in issues:
            return {
                "message": "Issue already added"
            }

        issues.append(
            issue.issue_id
        )

        SprintRepository.update_sprint(

            sprint_id,

            {
                "issues": issues,
                "updated_at": datetime.utcnow()
            }

        )

        return {
            "message": "Issue added to sprint successfully"
        }

    @staticmethod
    def remove_issue(
        sprint_id: str,
        issue
    ):

        sprint = SprintRepository.get_sprint_by_id(
            sprint_id
        )

        if not sprint:
            return {
                "message": "Sprint not found"
            }

        issues = sprint.get(
            "issues",
            []
        )

        if issue.issue_id not in issues:
            return {
                "message": "Issue not found in sprint"
            }

        issues.remove(
            issue.issue_id
        )

        SprintRepository.update_sprint(

            sprint_id,

            {
                "issues": issues,
                "updated_at": datetime.utcnow()
            }

        )

        return {
            "message": "Issue removed from sprint successfully"
        }

    @staticmethod
    def search_by_project(
        project_id: str
    ):

        sprints = SprintRepository.get_by_project(
            project_id
        )

        for sprint in sprints:

            sprint["_id"] = str(
                sprint["_id"]
            )

            project = ProjectRepository.get_project_by_id(
                sprint["project_id"]
            )

            sprint["project_name"] = (
                project["name"]
                if project else "N/A"
            )

        return sprints

    @staticmethod
    def search_by_status(
        status: str
    ):

        sprints = SprintRepository.get_by_status(
            status.upper()
        )

        for sprint in sprints:

            sprint["_id"] = str(
                sprint["_id"]
            )

            project = ProjectRepository.get_project_by_id(
                sprint["project_id"]
            )

            sprint["project_name"] = (
                project["name"]
                if project else "N/A"
            )

        return sprints