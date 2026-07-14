from pydantic import BaseModel


class IssueCreate(BaseModel):
    title: str
    description: str
    project_id: str
    assignee: str
    priority: str


class IssueStatusUpdate(BaseModel):
    status: str