from fastapi import APIRouter, Depends

from app.schemas.issue_schema import (
    IssueCreate,
    IssueUpdate,
    IssueStatusUpdate,
)

from app.services.issue_service import IssueService

from app.api.dependencies.auth import (
    get_current_admin,
    get_current_user,
    get_admin_or_member,
)

router = APIRouter(
    prefix="/issues",
    tags=["Issues"]
)



@router.post("/")
def create_issue(
    issue: IssueCreate,
    current_user=Depends(get_admin_or_member)
):
    return IssueService.create_issue(issue)



@router.get("/")
def get_all_issues(
    current_user=Depends(get_current_user)
):
    return IssueService.get_all_issues(
        current_user
    )



@router.get("/{issue_id}")
def get_issue_by_id(
    issue_id: str,
    current_user=Depends(get_current_user)
):
    return IssueService.get_issue_by_id(
        issue_id
    )



@router.put("/{issue_id}")
def update_issue(
    issue_id: str,
    issue: IssueUpdate,
    current_admin=Depends(get_current_admin)
):
    return IssueService.update_issue(
        issue_id,
        issue
    )



@router.delete("/{issue_id}")
def delete_issue(
    issue_id: str,
    current_admin=Depends(get_current_admin)
):
    return IssueService.delete_issue(
        issue_id
    )



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



@router.get("/search/status/{status}")
def search_by_status(
    status: str,
    current_user=Depends(get_current_user)
):
    return IssueService.search_by_status(
        status
    )


@router.get("/search/priority/{priority}")
def search_by_priority(
    priority: str,
    current_user=Depends(get_current_user)
):
    return IssueService.search_by_priority(
        priority
    )


@router.get("/search/assignee/{assignee}")
def search_by_assignee(
    assignee: str,
    current_user=Depends(get_current_user)
):
    return IssueService.search_by_assignee(
        assignee
    )



@router.get("/search/project/{project_id}")
def search_by_project(
    project_id: str,
    current_user=Depends(get_current_user)
):
    return IssueService.search_by_project(
        project_id
    )



@router.get("/project/{project_id}/parents")
def get_parent_issues(
    project_id: str,
    current_user=Depends(get_current_user)
):
    return IssueService.get_parent_issues(
        project_id
    )



@router.get("/{parent_issue_id}/children")
def get_child_issues(
    parent_issue_id: str,
    current_user=Depends(get_current_user)
):
    return IssueService.get_child_issues(
        parent_issue_id
    )