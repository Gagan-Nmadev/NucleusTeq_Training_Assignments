import uuid
from fastapi.testclient import TestClient
from app.main import app
from app.database.connection import db

client = TestClient(app)


def create_admin():

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
            "description": "Demo Project",
            "members": []
        }
    )

    project = db.projects.find_one(
        sort=[("_id", -1)]
    )

    return str(project["_id"])


def create_issue(headers, project_id):

    client.post(
        "/issues/",
        headers=headers,
        json={
            "title": "Bug",
            "description": "Demo Bug",
            "project_id": project_id,
            "assignee": "admin@gmail.com",
            "priority": "HIGH"
        }
    )

    issue = db.issues.find_one(
        sort=[("_id", -1)]
    )

    return str(issue["_id"])


def test_create_sprint():

    headers = create_admin()

    project_id = create_project(headers)

    response = client.post(
        "/sprints/",
        headers=headers,
        json={
            "name": "Sprint 1",
            "project_id": project_id,
            "start_date": "2026-07-07",
            "end_date": "2026-07-20"
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Sprint created successfully"


def test_add_issue():

    headers = create_admin()

    project_id = create_project(headers)

    issue_id = create_issue(
        headers,
        project_id
    )

    client.post(
        "/sprints/",
        headers=headers,
        json={
            "name": "Sprint 2",
            "project_id": project_id,
            "start_date": "2026-07-07",
            "end_date": "2026-07-20"
        }
    )

    sprint = db.sprints.find_one(
        sort=[("_id", -1)]
    )

    response = client.put(
        f"/sprints/{sprint['_id']}/add-issue",
        headers=headers,
        json={
            "issue_id": issue_id
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Issue added successfully"


def test_remove_issue():

    headers = create_admin()

    project_id = create_project(headers)

    issue_id = create_issue(
        headers,
        project_id
    )

    client.post(
        "/sprints/",
        headers=headers,
        json={
            "name": "Sprint 3",
            "project_id": project_id,
            "start_date": "2026-07-07",
            "end_date": "2026-07-20"
        }
    )

    sprint = db.sprints.find_one(
        sort=[("_id", -1)]
    )

    client.put(
        f"/sprints/{sprint['_id']}/add-issue",
        headers=headers,
        json={
            "issue_id": issue_id
        }
    )

    response = client.put(
        f"/sprints/{sprint['_id']}/remove-issue",
        headers=headers,
        json={
            "issue_id": issue_id
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Issue removed successfully"