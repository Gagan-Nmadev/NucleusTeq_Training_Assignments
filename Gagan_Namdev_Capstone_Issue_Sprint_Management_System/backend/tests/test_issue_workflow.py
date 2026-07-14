import uuid
from fastapi.testclient import TestClient
from app.main import app

client = TestClient(app)


def create_user(role="member"):

    email = f"{uuid.uuid4().hex[:6]}@gmail.com"

    client.post(
        "/users/register",
        json={
            "name": "User",
            "email": email,
            "password": "123456",
            "role": role
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

    return email, {
        "Authorization": f"Bearer {token}"
    }


def create_project(headers):

    name = f"Project_{uuid.uuid4().hex[:6]}"

    client.post(
        "/projects/",
        headers=headers,
        json={
            "name": name,
            "description": "Demo Project",
            "members": []
        }
    )

    projects = client.get(
        "/projects/",
        headers=headers
    ).json()

    return projects[-1]["_id"]


def create_issue(headers, assignee_email):

    project_id = create_project(headers)

    client.post(
        "/issues/",
        headers=headers,
        json={
            "title": "Login Bug",
            "description": "Demo",
            "project_id": project_id,
            "assignee": assignee_email,
            "priority": "HIGH"
        }
    )

    # Last inserted issue
    from app.database.connection import db

    issue = db.issues.find_one(
        sort=[("_id", -1)]
    )

    return str(issue["_id"])


def test_valid_status_transition():

    admin_email, admin_headers = create_user("admin")

    issue_id = create_issue(
        admin_headers,
        admin_email
    )

    response = client.put(
        f"/issues/{issue_id}/status",
        headers=admin_headers,
        json={
            "status": "IN_PROGRESS"
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Issue status updated successfully"


def test_invalid_transition():

    admin_email, admin_headers = create_user("admin")

    issue_id = create_issue(
        admin_headers,
        admin_email
    )

    client.put(
        f"/issues/{issue_id}/status",
        headers=admin_headers,
        json={
            "status": "IN_PROGRESS"
        }
    )

    client.put(
        f"/issues/{issue_id}/status",
        headers=admin_headers,
        json={
            "status": "DONE"
        }
    )

    response = client.put(
        f"/issues/{issue_id}/status",
        headers=admin_headers,
        json={
            "status": "TODO"
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Invalid status transition"


def test_non_assignee_update():

    admin_email, admin_headers = create_user("admin")

    issue_id = create_issue(
        admin_headers,
        admin_email
    )

    _, member_headers = create_user()

    response = client.put(
        f"/issues/{issue_id}/status",
        headers=member_headers,
        json={
            "status": "IN_PROGRESS"
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Only assignee can update issue status"