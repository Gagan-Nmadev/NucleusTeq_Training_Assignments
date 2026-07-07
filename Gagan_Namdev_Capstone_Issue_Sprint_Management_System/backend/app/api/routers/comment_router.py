from fastapi import APIRouter, Depends

from app.schemas.comment_schema import (
    CommentCreate,
    CommentUpdate,
)
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


@router.put("/{comment_id}")
def update_comment(
    comment_id: str,
    comment: CommentUpdate,
    current_user=Depends(get_current_user)
):
    return CommentService.update_comment(
        comment_id,
        comment,
        current_user
    )


@router.delete("/{comment_id}")
def delete_comment(
    comment_id: str,
    current_user=Depends(get_current_user)
):
    return CommentService.delete_comment(
        comment_id,
        current_user
    )