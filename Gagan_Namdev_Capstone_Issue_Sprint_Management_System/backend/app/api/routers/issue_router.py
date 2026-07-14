from fastapi import APIRouter, Depends

from app.schemas.issue_schema import (
    IssueCreate,
    IssueStatusUpdate,
)
from app.services.issue_service import IssueService
from app.api.dependencies.auth import (
    get_current_admin,
    get_current_user,
)

router = APIRouter(
    prefix="/issues",
    tags=["Issues"]
)


@router.post("/")
def create_issue(
    issue: IssueCreate,
    current_admin=Depends(get_current_admin)
):
    return IssueService.create_issue(issue)


@router.put("/{issue_id}/status")
def update_issue_status(
    issue_id: str,
    status: IssueStatusUpdate,
    current_user=Depends(get_current_user)
):
    return IssueService.update_issue_status(
        issue_id,
        status,
        current_user
    )