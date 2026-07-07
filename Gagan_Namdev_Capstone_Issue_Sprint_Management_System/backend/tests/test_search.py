from fastapi.testclient import TestClient
from app.main import app

client = TestClient(app)


def test_search_status():

    response = client.get(
        "/issues/search/status/TODO"
    )

    assert response.status_code == 200


def test_search_priority():

    response = client.get(
        "/issues/search/priority/HIGH"
    )

    assert response.status_code == 200


def test_search_assignee():

    response = client.get(
        "/issues/search/assignee/admin@gmail.com"
    )

    assert response.status_code == 200


def test_search_project():

    response = client.get(
        "/issues/search/project/test"
    )

    assert response.status_code == 200