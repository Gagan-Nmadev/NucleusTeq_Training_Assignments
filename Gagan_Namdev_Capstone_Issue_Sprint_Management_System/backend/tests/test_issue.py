import uuid
from fastapi.testclient import TestClient
from app.main import app

client = TestClient(app)


def get_admin_token():

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

    response = client.post(
        "/users/login",
        json={
            "email": email,
            "password": "123456"
        }
    )

    token = response.json()["access_token"]

    return {
        "Authorization": f"Bearer {token}"
    }


def create_project(headers):

    project_name = f"Project_{uuid.uuid4().hex[:6]}"

    client.post(
        "/projects/",
        headers=headers,
        json={
            "name": project_name,
            "description": "Backend Project",
            "members": []
        }
    )

    projects = client.get(
        "/projects/",
        headers=headers
    ).json()

    return projects[-1]["_id"]


def test_create_issue():

    headers = get_admin_token()

    project_id = create_project(headers)

    response = client.post(
        "/issues/",
        headers=headers,
        json={
            "title": "Login Bug",
            "description": "Unable to login",
            "project_id": project_id,
            "assignee": "gagan@gmail.com",
            "priority": "HIGH"
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Issue created successfully"


def test_invalid_project():

    headers = get_admin_token()

    response = client.post(
        "/issues/",
        headers=headers,
        json={
            "title": "Bug",
            "description": "Demo",
            "project_id": "686000000000000000000000",
            "assignee": "gagan@gmail.com",
            "priority": "LOW"
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Project not found"