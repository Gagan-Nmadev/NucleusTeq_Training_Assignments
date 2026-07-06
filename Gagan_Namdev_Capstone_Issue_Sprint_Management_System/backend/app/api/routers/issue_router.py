from fastapi import APIRouter, Depends

from app.schemas.issue_schema import IssueCreate
from app.services.issue_service import IssueService
from app.api.dependencies.auth import get_current_admin


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