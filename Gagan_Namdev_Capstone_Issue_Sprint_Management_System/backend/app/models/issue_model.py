from datetime import datetime


class IssueModel:

    @staticmethod
    def create_issue(
        title,
        description,
        project_id,
        assignee,
        priority,
        issue_type,
        parent_issue_id=None,
        due_date=None
    ):

        return {

            "title": title,

            "description": description,

            "project_id": project_id,

            "assignee": assignee,

            "priority": priority,

            "issue_type": issue_type,

            "parent_issue_id": parent_issue_id,

            "due_date": due_date,

            "status": "TODO",

            "created_at": datetime.utcnow(),

            "updated_at": datetime.utcnow()

        }