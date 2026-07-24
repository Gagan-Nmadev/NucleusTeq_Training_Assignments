import uuid
from fastapi.testclient import TestClient
from app.main import app

client = TestClient(app)


def test_admin_access():

    email = f"admin_{uuid.uuid4().hex[:6]}@gmail.com"

    client.post(
        "/users/register",
        json={
            "name": "Admin",
            "email": email,
            "password": "123456",
            "role": "admin"
        }
    )

    login = client.post(
        "/users/login",
        json={
            "email": email,
            "password": "123456"
        }
    )

    token = login.json()["access_token"]

    response = client.get(
        "/admin/users",
        headers={
            "Authorization": f"Bearer {token}"
        }
    )

    assert response.status_code == 200


def test_member_blocked():

    email = f"member_{uuid.uuid4().hex[:6]}@gmail.com"

    client.post(
        "/users/register",
        json={
            "name": "Member",
            "email": email,
            "password": "123456",
            "role": "member"
        }
    )

    login = client.post(
        "/users/login",
        json={
            "email": email,
            "password": "123456"
        }
    )

    token = login.json()["access_token"]

    response = client.get(
        "/admin/users",
        headers={
            "Authorization": f"Bearer {token}"
        }
    )

    assert response.status_code == 403
    assert response.json()["detail"] == "Admin access required"


def test_invalid_token():

    response = client.get(
        "/admin/users",
        headers={
            "Authorization": "Bearer invalidtoken123"
        }
    )

    assert response.status_code == 401
    assert response.json()["detail"] == "Invalid Token"


def test_missing_token():

    response = client.get("/admin/users")

    assert response.status_code in [401, 403]