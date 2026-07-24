from fastapi import Depends, HTTPException
from fastapi.security import (
    HTTPBearer,
    HTTPAuthorizationCredentials,
)

from app.core.security import decode_access_token
from app.repositories.user_repository import UserRepository


security = HTTPBearer()


def get_current_user(
    credentials: HTTPAuthorizationCredentials = Depends(security)
):

    token = credentials.credentials

    payload = decode_access_token(token)

    if payload is None:
        raise HTTPException(
            status_code=401,
            detail="Invalid token"
        )

    email = payload.get("sub")

    if email is None:
        raise HTTPException(
            status_code=401,
            detail="Invalid token payload"
        )

    user = UserRepository.get_user_by_email(email)

    if user is None:
        raise HTTPException(
            status_code=404,
            detail="User not found"
        )

    return user


def get_current_admin(
    current_user=Depends(get_current_user)
):

    if current_user["role"] != "admin":
        raise HTTPException(
            status_code=403,
            detail="Admin access required"
        )

    return current_user


def get_current_member(
    current_user=Depends(get_current_user)
):

    if current_user["role"] != "member":
        raise HTTPException(
            status_code=403,
            detail="Member access required"
        )

    return current_user


def get_current_viewer(
    current_user=Depends(get_current_user)
):

    if current_user["role"] != "viewer":
        raise HTTPException(
            status_code=403,
            detail="Viewer access required"
        )

    return current_user


def get_admin_or_member(
    current_user=Depends(get_current_user)
):

    if current_user["role"] not in [
        "admin",
        "member",
    ]:
        raise HTTPException(
            status_code=403,
            detail="Access denied"
        )

    return current_user


def get_authenticated_user(
    current_user=Depends(get_current_user)
):

    return current_user