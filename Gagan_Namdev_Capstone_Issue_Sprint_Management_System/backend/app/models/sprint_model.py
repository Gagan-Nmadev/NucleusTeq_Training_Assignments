from datetime import datetime


class SprintModel:

    @staticmethod
    def create_sprint(
        name,
        description,
        goal,
        project_id,
        start_date,
        end_date
    ):

        return {

            "name": name,

            "description": description,

            "goal": goal,

            "project_id": project_id,

            "start_date": start_date,

            "end_date": end_date,

            "status": "PLANNED",

            "issues": [],

            "created_at": datetime.utcnow(),

            "updated_at": datetime.utcnow(),

            "started_at": None,

            "completed_at": None

        }