from app.models.issue_model import IssueModel
from app.repositories.issue_repository import IssueRepository
from app.repositories.project_repository import ProjectRepository


class IssueService:

    @staticmethod
    def create_issue(issue):

        project = ProjectRepository.get_project_by_id(
            issue.project_id
        )

        if not project:
            return {
                "message": "Project not found"
            }

        issue_data = IssueModel.create_issue(
            title=issue.title,
            description=issue.description,
            project_id=issue.project_id,
            assignee=issue.assignee,
            priority=issue.priority
        )

        IssueRepository.create_issue(issue_data)

        return {
            "message": "Issue created successfully"
        }

    @staticmethod
    def update_issue_status(issue_id: str, status_data):

        issue = IssueRepository.get_issue_by_id(issue_id)

        if not issue:
            return {
                "message": "Issue not found"
            }

        current_status = issue["status"]
        new_status = status_data.status

        valid_transitions = {
            "TODO": ["IN_PROGRESS"],
            "IN_PROGRESS": ["DONE"],
            "DONE": []
        }

        if new_status not in valid_transitions[current_status]:
            return {
                "message": "Invalid status transition"
            }

        IssueRepository.update_issue(
            issue_id,
            {
                "status": new_status
            }
        )

        return {
            "message": "Issue status updated successfully"
        }