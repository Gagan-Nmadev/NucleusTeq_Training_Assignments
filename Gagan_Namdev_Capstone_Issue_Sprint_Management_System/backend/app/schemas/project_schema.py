from pydantic import BaseModel
from typing import List


class ProjectCreate(BaseModel):
    name: str
    description: str
    members: List[str] = []


class ProjectUpdate(BaseModel):
    name: str
    description: str
    members: List[str] = []


class AssignMembers(BaseModel):
    members: List[str]


class ProjectResponse(BaseModel):
    id: str
    name: str
    description: str
    members: List[str]