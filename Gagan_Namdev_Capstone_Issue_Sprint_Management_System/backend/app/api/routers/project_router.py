from fastapi import APIRouter, Depends

from app.schemas.project_schema import (
    ProjectCreate,
    ProjectUpdate,
    AssignMembers,
)

from app.services.project_service import ProjectService

from app.api.dependencies.auth import (
    get_current_admin,
    get_authenticated_user,
)

router = APIRouter(
    prefix="/projects",
    tags=["Projects"]
)




@router.post("/")
def create_project(
    project: ProjectCreate,
    current_admin=Depends(get_current_admin)
):
    return ProjectService.create_project(project)




@router.get("/")
def get_all_projects(
    current_user=Depends(get_authenticated_user)
):

    if current_user["role"] == "admin":
        return ProjectService.get_all_projects()

    return ProjectService.get_my_projects(current_user)




@router.get("/my")
def get_my_projects(
    current_user=Depends(get_authenticated_user)
):
    return ProjectService.get_my_projects(current_user)



@router.get("/{project_id}")
def get_project_by_id(
    project_id: str,
    current_user=Depends(get_authenticated_user)
):
    return ProjectService.get_project_by_id(project_id)



@router.put("/{project_id}")
def update_project(
    project_id: str,
    project: ProjectUpdate,
    current_admin=Depends(get_current_admin)
):
    return ProjectService.update_project(
        project_id,
        project
    )




@router.delete("/{project_id}")
def delete_project(
    project_id: str,
    current_admin=Depends(get_current_admin)
):
    return ProjectService.delete_project(project_id)




@router.put("/{project_id}/members")
def assign_members(
    project_id: str,
    data: AssignMembers,
    current_admin=Depends(get_current_admin)
):
    return ProjectService.assign_members(
        project_id,
        data
    )