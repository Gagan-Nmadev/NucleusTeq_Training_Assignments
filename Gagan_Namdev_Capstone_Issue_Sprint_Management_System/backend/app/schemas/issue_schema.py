from typing import Optional
from pydantic import BaseModel




class IssueCreate(BaseModel):

    title: str
    description: str

    project_id: str

    assignee: str

    priority: str

    issue_type: str

    parent_issue_id: Optional[str] = None

    due_date: Optional[str] = None




class IssueUpdate(BaseModel):

    title: str

    description: str

    assignee: str

    priority: str

    issue_type: str

    parent_issue_id: Optional[str] = None

    due_date: Optional[str] = None




class IssueStatusUpdate(BaseModel):

    status: str