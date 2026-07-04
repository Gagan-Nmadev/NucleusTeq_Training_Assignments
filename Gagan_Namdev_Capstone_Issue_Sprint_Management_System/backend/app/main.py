from fastapi import FastAPI
from app.core.config import settings
from app.database.connection import client

app = FastAPI(title=settings.APP_NAME)


@app.get("/")
def home():
    client.admin.command("ping")
    return {
        "message": "MongoDB Connected Successfully"
    }