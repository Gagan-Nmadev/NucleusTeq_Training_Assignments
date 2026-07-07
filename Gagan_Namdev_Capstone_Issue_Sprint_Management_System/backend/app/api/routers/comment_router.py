from fastapi import APIRouter, Depends

from app.schemas.comment_schema import CommentCreate
from app.services.comment_service import CommentService
from app.api.dependencies.auth import get_current_user


router = APIRouter(
    prefix="/comments",
    tags=["Comments"]
)


@router.post("/")
def create_comment(
    comment: CommentCreate,
    current_user=Depends(get_current_user)
):
    return CommentService.create_comment(
        comment,
        current_user
    )


@router.get("/{issue_id}")
def get_comments(issue_id: str):
    return CommentService.get_comments(issue_id)