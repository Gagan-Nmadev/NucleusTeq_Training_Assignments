from datetime import datetime


class IssueModel:

    @staticmethod
    def create_issue(
        title,
        description,
        project_id,
        assignee,
        priority
    ):
        return {
            "title": title,
            "description": description,
            "project_id": project_id,
            "assignee": assignee,
            "priority": priority,
            "status": "TODO",
            "created_at": datetime.utcnow()
        }