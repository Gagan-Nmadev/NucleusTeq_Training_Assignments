from datetime import datetime

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
            priority=issue.priority,
            issue_type=issue.issue_type,
            parent_issue_id=issue.parent_issue_id,
            due_date=issue.due_date
        )

        IssueRepository.create_issue(issue_data)

        return {
            "message": "Issue created successfully"
        }

    @staticmethod
    def get_all_issues(current_user):

        issues = IssueRepository.get_all_issues()

        result = []

        for issue in issues:

            project = ProjectRepository.get_project_by_id(
                issue["project_id"]
            )

            issue_data = {
                "_id": str(issue["_id"]),
                "title": issue.get("title"),
                "description": issue.get("description"),
                "project_id": issue.get("project_id"),
                "project_name": (
                    project["name"]
                    if project else "N/A"
                ),
                "assignee": issue.get("assignee"),
                "priority": issue.get("priority"),
                "issue_type": issue.get("issue_type"),
                "status": issue.get("status"),
                "parent_issue_id": issue.get(
                    "parent_issue_id"
                ),
                "due_date": issue.get("due_date"),
                "created_at": issue.get("created_at"),
                "updated_at": issue.get("updated_at")
            }

            # Admin -> All Issues
            if current_user["role"] == "admin":
                result.append(issue_data)

            # Member -> Assigned Issues
            elif (
                current_user["role"] == "member"
                and
                issue.get("assignee")
                == current_user["email"]
            ):
                result.append(issue_data)

            # Viewer -> Read Only Assigned Issues
            elif (
                current_user["role"] == "viewer"
                and
                issue.get("assignee")
                == current_user["email"]
            ):
                result.append(issue_data)

        return result

    @staticmethod
    def get_issue_by_id(issue_id: str):

        issue = IssueRepository.get_issue_by_id(
            issue_id
        )

        if not issue:
            return {
                "message": "Issue not found"
            }

        issue["_id"] = str(issue["_id"])

        return issue
    @staticmethod
    def get_all_issues(current_user):

        issues = IssueRepository.get_all_issues()

        result = []

        for issue in issues:

            project = ProjectRepository.get_project_by_id(
                issue["project_id"]
            )

            if project:
                issue["project_name"] = project["name"]
            else:
                issue["project_name"] = "N/A"

            issue["_id"] = str(issue["_id"])

            if current_user["role"] == "admin":

                result.append(issue)

            elif current_user["email"] == issue.get("assignee"):

                result.append(issue)

            elif project and current_user["email"] in project.get("members", []):

                result.append(issue)

        return result


    @staticmethod
    def get_issue_by_id(issue_id: str):

        issue = IssueRepository.get_issue_by_id(
            issue_id
        )

        if not issue:

            return {
                "message": "Issue not found"
            }

        issue["_id"] = str(issue["_id"])

        project = ProjectRepository.get_project_by_id(
            issue["project_id"]
        )

        if project:

            issue["project_name"] = project["name"]

        else:

            issue["project_name"] = "N/A"

        return issue


    @staticmethod
    def update_issue(issue_id: str, issue):

        existing_issue = IssueRepository.get_issue_by_id(
            issue_id
        )

        if not existing_issue:

            return {
                "message": "Issue not found"
            }

        IssueRepository.update_issue(

            issue_id,

            {

                "title": issue.title,

                "description": issue.description,

                "assignee": issue.assignee,

                "priority": issue.priority,

                "issue_type": issue.issue_type,

                "parent_issue_id": issue.parent_issue_id,

                "due_date": issue.due_date,

                "updated_at": datetime.utcnow()

            }

        )

        return {

            "message": "Issue updated successfully"

        }


    @staticmethod
    def delete_issue(issue_id: str):

        issue = IssueRepository.get_issue_by_id(
            issue_id
        )

        if not issue:

            return {
                "message": "Issue not found"
            }

        IssueRepository.delete_issue(
            issue_id
        )

        return {
            "message": "Issue deleted successfully"
        }


    @staticmethod
    def update_issue_status(
        issue_id: str,
        status_data,
        current_user
    ):

        issue = IssueRepository.get_issue_by_id(
            issue_id
        )

        if not issue:

            return {
                "message": "Issue not found"
            }

   
        if (

            current_user["role"] != "admin"

            and

            issue["assignee"] != current_user["email"]

        ):

            return {
                "message": "Only assignee can update issue status"
            }

        current_status = issue["status"]

        new_status = status_data.status

        valid_transitions = {

            "TODO": [
                "IN_PROGRESS"
            ],

            "IN_PROGRESS": [
                "DONE"
            ],

            "DONE": []

        }

        if new_status not in valid_transitions.get(
            current_status,
            []
        ):

            return {
                "message": "Invalid status transition"
            }

        IssueRepository.update_issue(

            issue_id,

            {

                "status": new_status,

                "updated_at": datetime.utcnow()

            }

        )

        return {

            "message": "Issue status updated successfully"

        }


    @staticmethod
    def search_by_status(status: str):

        issues = IssueRepository.get_by_status(
            status
        )

        for issue in issues:

            issue["_id"] = str(issue["_id"])

        return issues


    @staticmethod
    def search_by_priority(priority: str):

        issues = IssueRepository.get_by_priority(
            priority
        )

        for issue in issues:

            issue["_id"] = str(issue["_id"])

        return issues


    @staticmethod
    def search_by_assignee(assignee: str):

        issues = IssueRepository.get_by_assignee(
            assignee
        )

        for issue in issues:

            issue["_id"] = str(issue["_id"])

        return issues


    @staticmethod
    def search_by_project(project_id: str):

        issues = IssueRepository.get_by_project(
            project_id
        )

        for issue in issues:

            issue["_id"] = str(issue["_id"])

            project = ProjectRepository.get_project_by_id(
                issue["project_id"]
            )

            issue["project_name"] = (

                project["name"]

                if project

                else "N/A"

            )

        return issues


    @staticmethod
    def get_parent_issues(project_id: str):

        issues = IssueRepository.get_parent_issues(
            project_id
        )

        for issue in issues:

            issue["_id"] = str(issue["_id"])

        return issues


    @staticmethod
    def get_child_issues(parent_issue_id: str):

        issues = IssueRepository.get_child_issues(
            parent_issue_id
        )

        for issue in issues:

            issue["_id"] = str(issue["_id"])

        return issues