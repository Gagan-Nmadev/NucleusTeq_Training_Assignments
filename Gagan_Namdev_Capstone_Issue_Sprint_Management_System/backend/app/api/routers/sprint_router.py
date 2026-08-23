from fastapi import APIRouter, Depends

from app.schemas.sprint_schema import (
    SprintCreate,
    SprintUpdate,
    SprintIssue,
)

from app.services.sprint_service import SprintService

from app.api.dependencies.auth import (
    get_current_admin,
)

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
def get_sprint_by_id(
    sprint_id: str
):
    return SprintService.get_sprint_by_id(
        sprint_id
    )


@router.put("/{sprint_id}")
def update_sprint(
    sprint_id: str,
    sprint: SprintUpdate,
    current_admin=Depends(get_current_admin)
):
    return SprintService.update_sprint(
        sprint_id,
        sprint
    )


@router.delete("/{sprint_id}")
def delete_sprint(
    sprint_id: str,
    current_admin=Depends(get_current_admin)
):
    return SprintService.delete_sprint(
        sprint_id
    )


@router.put("/{sprint_id}/start")
def start_sprint(
    sprint_id: str,
    current_admin=Depends(get_current_admin)
):
    return SprintService.start_sprint(
        sprint_id
    )


@router.put("/{sprint_id}/complete")
def complete_sprint(
    sprint_id: str,
    current_admin=Depends(get_current_admin)
):
    return SprintService.complete_sprint(
        sprint_id
    )


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


# Search routes
@router.get("/search/project/{project_id}")
def search_by_project(
    project_id: str
):
    return SprintService.search_by_project(
        project_id
    )


@router.get("/search/status/{status}")
def search_by_status(
    status: str
):
    return SprintService.search_by_status(
        status
    )