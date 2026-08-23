import uuid
from fastapi.testclient import TestClient
from app.main import app
from app.database.connection import db

client = TestClient(app)


def create_user():

    email = f"user_{uuid.uuid4().hex[:6]}@gmail.com"

    client.post(
        "/users/register",
        json={
            "name": "Test User",
            "email": email,
            "password": "123456",
            "role": "member"
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
    }, email


def create_project_and_issue():

    admin_email = f"admin_{uuid.uuid4().hex[:6]}@gmail.com"

    client.post(
        "/users/register",
        json={
            "name": "Admin",
            "email": admin_email,
            "password": "123456",
            "role": "admin"
        }
    )

    login = client.post(
        "/users/login",
        json={
            "email": admin_email,
            "password": "123456"
        }
    )

    headers = {
        "Authorization": f"Bearer {login.json()['access_token']}"
    }

    client.post(
        "/projects/",
        headers=headers,
        json={
            "name": f"Project_{uuid.uuid4().hex[:6]}",
            "description": "Demo",
            "members": []
        }
    )

    project = db.projects.find_one(sort=[("_id", -1)])

    client.post(
        "/issues/",
        headers=headers,
        json={
            "title": "Bug",
            "description": "Demo",
            "project_id": str(project["_id"]),
            "assignee": admin_email,
            "priority": "HIGH"
        }
    )

    issue = db.issues.find_one(sort=[("_id", -1)])

    return str(issue["_id"])


def test_create_comment():

    issue_id = create_project_and_issue()

    headers, _ = create_user()

    response = client.post(
        "/comments/",
        headers=headers,
        json={
            "issue_id": issue_id,
            "comment": "First Comment"
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Comment added successfully"