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


def test_create_project():

    headers = get_admin_token()

    project_name = f"Project_{uuid.uuid4().hex[:6]}"

    response = client.post(
        "/projects/",
        headers=headers,
        json={
            "name": project_name,
            "description": "Backend Project",
            "members": []
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Project created successfully"


def test_get_all_projects():

    headers = get_admin_token()

    response = client.get(
        "/projects/",
        headers=headers
    )

    assert response.status_code == 200
    assert isinstance(response.json(), list)


def test_get_project_by_id():

    headers = get_admin_token()

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

    project_id = projects[-1]["_id"]

    response = client.get(
        f"/projects/{project_id}",
        headers=headers
    )

    assert response.status_code == 200
    assert response.json()["_id"] == project_id


def test_update_project():

    headers = get_admin_token()

    project_name = f"Project_{uuid.uuid4().hex[:6]}"

    client.post(
        "/projects/",
        headers=headers,
        json={
            "name": project_name,
            "description": "Backend",
            "members": []
        }
    )

    projects = client.get(
        "/projects/",
        headers=headers
    ).json()

    project_id = projects[-1]["_id"]

    response = client.put(
        f"/projects/{project_id}",
        headers=headers,
        json={
            "name": "Updated Project",
            "description": "Updated Description",
            "members": []
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Project updated successfully"


def test_assign_members():

    headers = get_admin_token()

    project_name = f"Project_{uuid.uuid4().hex[:6]}"

    client.post(
        "/projects/",
        headers=headers,
        json={
            "name": project_name,
            "description": "Backend",
            "members": []
        }
    )

    projects = client.get(
        "/projects/",
        headers=headers
    ).json()

    project_id = projects[-1]["_id"]

    response = client.put(
        f"/projects/{project_id}/members",
        headers=headers,
        json={
            "members": [
                "gagan@gmail.com",
                "shubh@gmail.com"
            ]
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Members assigned successfully"


def test_delete_project():

    headers = get_admin_token()

    project_name = f"Project_{uuid.uuid4().hex[:6]}"

    client.post(
        "/projects/",
        headers=headers,
        json={
            "name": project_name,
            "description": "Backend",
            "members": []
        }
    )

    projects = client.get(
        "/projects/",
        headers=headers
    ).json()

    project_id = projects[-1]["_id"]

    response = client.delete(
        f"/projects/{project_id}",
        headers=headers
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Project deleted successfully"