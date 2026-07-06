from fastapi import APIRouter, Depends

from app.schemas.project_schema import ProjectCreate
from app.services.project_service import ProjectService
from app.api.dependencies.auth import get_current_admin


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
def get_all_projects():
    return ProjectService.get_all_projects()


@router.get("/{project_id}")
def get_project_by_id(project_id: str):
    return ProjectService.get_project_by_id(project_id)