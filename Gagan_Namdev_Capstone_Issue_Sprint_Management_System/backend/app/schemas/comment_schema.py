from pydantic import BaseModel


class CommentCreate(BaseModel):
    issue_id: str
    comment: str


class CommentUpdate(BaseModel):
    comment: str