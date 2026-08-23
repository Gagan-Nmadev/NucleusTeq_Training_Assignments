from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from app.core.config import settings
from app.database.connection import client

from app.api.routers.user_router import router as user_router
from app.api.routers.admin_router import router as admin_router
from app.api.routers.project_router import router as project_router
from app.api.routers.issue_router import router as issue_router
from app.api.routers.sprint_router import router as sprint_router
from app.api.routers.comment_router import router as comment_router


app = FastAPI(title=settings.APP_NAME)


app.add_middleware(
    CORSMiddleware,
    allow_origins=[
        "http://localhost:5173",
        "http://127.0.0.1:5173",
        "http://localhost:5174",
        "http://127.0.0.1:5174",
    ],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.on_event("startup")
def startup():
    client.admin.command("ping")
    print("MongoDB Connected Successfully")


@app.on_event("shutdown")
def shutdown():
    client.close()
    print("MongoDB Disconnected")


@app.get("/")
def home():
    return {"message": "Issue Sprint Management System API"}


app.include_router(user_router)
app.include_router(admin_router)
app.include_router(project_router)
app.include_router(issue_router)
app.include_router(sprint_router)
app.include_router(comment_router)