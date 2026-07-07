from pydantic import BaseModel


class CommentCreate(BaseModel):
    issue_id: str
    comment: str