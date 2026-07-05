from app.repositories.user_repository import UserRepository
from app.models.user_model import UserModel
from app.core.security import (
    hash_password,
    verify_password,
    create_access_token,
)


class UserService:

    @staticmethod
    def register_user(user):

        existing_user = UserRepository.get_user_by_email(user.email)

        if existing_user:
            return {"message": "Email already registered"}

        hashed_password = hash_password(user.password)

        user_data = UserModel.create_user(
            name=user.name,
            email=user.email,
            password=hashed_password,
        )

        UserRepository.create_user(user_data)

        return {
            "message": "User Registered Successfully"
        }

    @staticmethod
    def login_user(user):

        existing_user = UserRepository.get_user_by_email(user.email)

        if not existing_user:
            return {"message": "Invalid Email"}

        if not verify_password(
            user.password,
            existing_user["password"],
        ):
            return {"message": "Invalid Password"}

        token = create_access_token(
            {"sub": existing_user["email"]}
        )

        return {
            "access_token": token,
            "token_type": "bearer",
        }