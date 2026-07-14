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