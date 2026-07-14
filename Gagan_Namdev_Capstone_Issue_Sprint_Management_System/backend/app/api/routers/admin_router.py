from fastapi import APIRouter, Depends

from app.api.dependencies.auth import get_current_admin
from app.database.connection import db

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
                "_id": 0,
                "password": 0
            }
        )
    )

    return users