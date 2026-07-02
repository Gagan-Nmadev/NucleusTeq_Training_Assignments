from fastapi import FastAPI

app = FastAPI(
    title="Issue & Sprint Management System API",
    version="1.0.0"
)


@app.get("/")
def root():
    return {
        "message": "Welcome to Issue & Sprint Management System API"
    }


@app.get("/health")
def health_check():
    return {
        "status": "Healthy"
    }