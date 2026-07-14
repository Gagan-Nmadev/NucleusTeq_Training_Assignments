from pydantic import BaseModel
from typing import List


class SprintCreate(BaseModel):
    name: str
    project_id: str
    start_date: str
    end_date: str


class SprintIssue(BaseModel):
    issue_id: str


class SprintResponse(BaseModel):
    id: str
    name: str
    project_id: str
    start_date: str
    end_date: str
    issues: List[str]