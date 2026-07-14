from fastapi import APIRouter, Depends

from app.schemas.sprint_schema import (
    SprintCreate,
    SprintIssue,
)
from app.services.sprint_service import SprintService
from app.api.dependencies.auth import get_current_admin


router = APIRouter(
    prefix="/sprints",
    tags=["Sprints"]
)


@router.post("/")
def create_sprint(
    sprint: SprintCreate,
    current_admin=Depends(get_current_admin)
):
    return SprintService.create_sprint(sprint)


@router.get("/")
def get_all_sprints():
    return SprintService.get_all_sprints()


@router.get("/{sprint_id}")
def get_sprint_by_id(sprint_id: str):
    return SprintService.get_sprint_by_id(sprint_id)


@router.put("/{sprint_id}/add-issue")
def add_issue(
    sprint_id: str,
    issue: SprintIssue,
    current_admin=Depends(get_current_admin)
):
    return SprintService.add_issue(
        sprint_id,
        issue
    )


@router.put("/{sprint_id}/remove-issue")
def remove_issue(
    sprint_id: str,
    issue: SprintIssue,
    current_admin=Depends(get_current_admin)
):
    return SprintService.remove_issue(
        sprint_id,
        issue
    )