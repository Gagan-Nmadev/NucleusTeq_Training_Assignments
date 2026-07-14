from fastapi import APIRouter, Depends

from app.schemas.user_schema import UserRegister, UserLogin
from app.services.user_service import UserService
from app.api.dependencies.auth import get_current_user


router = APIRouter(
    prefix="/users",
    tags=["Users"]
)


@router.post("/register")
def register(user: UserRegister):
    return UserService.register_user(user)


@router.post("/login")
def login(user: UserLogin):
    return UserService.login_user(user)


@router.get("/me")
def get_profile(current_user=Depends(get_current_user)):

    return {
        "name": current_user["name"],
        "email": current_user["email"]
    }