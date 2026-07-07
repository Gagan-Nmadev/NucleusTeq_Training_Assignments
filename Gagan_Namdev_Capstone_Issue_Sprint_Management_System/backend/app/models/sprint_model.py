from datetime import datetime


class SprintModel:

    @staticmethod
    def create_sprint(
        name,
        project_id,
        start_date,
        end_date
    ):
        return {
            "name": name,
            "project_id": project_id,
            "start_date": start_date,
            "end_date": end_date,
            "issues": [],
            "created_at": datetime.utcnow()
        }