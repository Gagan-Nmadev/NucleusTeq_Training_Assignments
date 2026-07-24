from datetime import date
from typing import List, Optional

from pydantic import BaseModel


class SprintCreate(BaseModel):

    name: str

    description: str

    goal: str

    project_id: str

    start_date: date

    end_date: date


class SprintUpdate(BaseModel):

    name: str

    description: str

    goal: str

    project_id: str

    start_date: date

    end_date: date


class SprintIssue(BaseModel):

    issue_id: str


class SprintStatusUpdate(BaseModel):

    status: str


class SprintResponse(BaseModel):

    id: str

    name: str

    description: str

    goal: str

    project_id: str

    project_name: Optional[str] = None

    start_date: date

    end_date: date

    status: str

    issues: List[str] = []

    completed_at: Optional[str] = None