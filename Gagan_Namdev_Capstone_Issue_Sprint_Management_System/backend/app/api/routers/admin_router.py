from fastapi import APIRouter, Depends
from bson import ObjectId
from pydantic import BaseModel

from app.api.dependencies.auth import get_current_admin
from app.database.connection import db
from app.services.user_service import UserService

class RoleUpdate(BaseModel):
    role: str

router = APIRouter(
    prefix="/admin",
    tags=["Admin"]
)


@router.get("/users")
def get_all_users(
    current_admin=Depends(get_current_admin)
):

    users = list(
        db.users.find(
            {},
            {
                "password": 0
            }
        )
    )

    for user in users:
        user["_id"] = str(user["_id"])

    return users


@router.delete("/users/{user_id}")
def delete_user(
    user_id: str,
    current_admin=Depends(get_current_admin)
):

    return UserService.delete_user(user_id)

@router.put("/users/{user_id}/role")
def update_user_role(
    user_id: str,
    data: RoleUpdate,
    current_admin=Depends(get_current_admin)
):

    user = db.users.find_one(
        {"_id": ObjectId(user_id)}
    )

    if not user:
        return {
            "message": "User not found"
        }

    if user["email"] == current_admin["email"]:
        return {
            "message": "You cannot change your own role."
        }

    db.users.update_one(
        {"_id": ObjectId(user_id)},
        {
            "$set": {
                "role": data.role.lower()
            }
        }
    )

    return {
        "message": "Role Updated Successfully"
    }