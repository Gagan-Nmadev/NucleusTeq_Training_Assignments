import uuid
from fastapi.testclient import TestClient
from app.main import app

client = TestClient(app)


def test_register_user():

    email = f"test_{uuid.uuid4().hex[:6]}@gmail.com"

    response = client.post(
        "/users/register",
        json={
            "name": "Test User",
            "email": email,
            "password": "123456"
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "User Registered Successfully"


def test_login_user():

    email = f"login_{uuid.uuid4().hex[:6]}@gmail.com"

    client.post(
        "/users/register",
        json={
            "name": "Login User",
            "email": email,
            "password": "123456"
        }
    )

    response = client.post(
        "/users/login",
        json={
            "email": email,
            "password": "123456"
        }
    )

    assert response.status_code == 200
    assert "access_token" in response.json()


def test_invalid_email():

    response = client.post(
        "/users/login",
        json={
            "email": "abc@gmail.com",
            "password": "123456"
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Invalid Email"


def test_invalid_password():

    email = f"wrong_{uuid.uuid4().hex[:6]}@gmail.com"

    client.post(
        "/users/register",
        json={
            "name": "Wrong Password",
            "email": email,
            "password": "123456"
        }
    )

    response = client.post(
        "/users/login",
        json={
            "email": email,
            "password": "999999"
        }
    )

    assert response.status_code == 200
    assert response.json()["message"] == "Invalid Password"