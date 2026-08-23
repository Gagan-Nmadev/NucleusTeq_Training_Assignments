# Pizza Delivery Pipeline - Apache Airflow Assignment

## Project Overview

This project demonstrates a pizza delivery workflow using Apache Airflow. The pipeline is containerized and executed using Docker.

The Airflow DAG can be triggered manually through the Airflow UI as well as through the Airflow REST API using Postman.

## Technologies Used

* Apache Airflow
* Docker
* Docker Compose
* Python
* REST API
* Postman
* YAML
* Git and GitHub

## Project Structure

```text
Gagan_Pizza_Airflow_Assignment/
│
├── dags/
│   └── gagan_pizza_delivery_pipeline.py
│
├── screenshots/
│   ├── 01_Airflow_DAG.png
│   ├── 02_DAG_Grid.png
│   ├── 03_Task_Logs.png
│   └── 04_Postman_API.png
│
├── README.md
├── .gitignore
└── docker-compose.yaml
```

Note: The Docker Compose YAML file is used locally to run the Airflow environment and is excluded from the GitHub repository as required.

## Airflow DAG

### DAG ID

```text
gagan_pizza_delivery_pipeline
```

The DAG represents the pizza delivery process using a sequence of Airflow tasks.

The workflow processes a pizza order through multiple stages defined in the DAG.

## Running the Project

Docker Desktop must be installed and running before starting the project.

Open a terminal inside the project directory and start the Airflow environment using Docker Compose.

```bash
docker compose up -d
```

After the containers are started, open the Airflow web interface:

```text
http://localhost:8080
```

Log in using the Airflow credentials configured in the local Docker setup.

## Triggering the DAG Using Postman

The Airflow DAG can be triggered using the Airflow REST API.

A POST request is sent from Postman with the pizza order information.

Example request body:

```json
{
    "conf": {
        "pizza": "Gagan's Special",
        "requested_by": "Gagan Namdev"
    }
}
```

The API response confirms that the DAG run has been created.

Example response:

```json
{
    "conf": {
        "pizza": "Gagan's Special",
        "requested_by": "Gagan Namdev"
    },
    "dag_id": "gagan_pizza_delivery_pipeline",
    "run_type": "manual",
    "external_trigger": true,
    "state": "queued"
}
```

The `queued` state indicates that Airflow has accepted the request and the DAG run is waiting for execution.

After scheduling, the DAG run should proceed to execution and eventually reach the `success` state if all tasks complete successfully.

## Monitoring the DAG

The DAG execution can be monitored from the Airflow web interface.

Open the `gagan_pizza_delivery_pipeline` DAG and use the Grid or Graph view to monitor the execution of individual tasks.

Each task displays its current execution state.

A successful pipeline execution results in all required tasks completing successfully.

## Task Logs

Airflow provides logs for each individual task.

The logs can be opened from the Airflow UI by selecting a task and opening its Logs section.

Task logs can be used to verify that the task was executed successfully and that the pizza order information was processed.

## API Trigger Flow

The overall execution flow of the project is:

```text
Postman
   |
   v
Airflow REST API
   |
   v
gagan_pizza_delivery_pipeline
   |
   v
Airflow Scheduler
   |
   v
Pizza Delivery Tasks
   |
   v
Successful Pipeline Execution
```

## Screenshots

The `screenshots` directory contains screenshots captured during the execution and testing of the project.

### Airflow DAG

Shows the pizza delivery DAG in the Airflow web interface.

### DAG Grid or Graph View

Shows the DAG tasks and their execution status.

### Task Logs

Shows the execution logs of an Airflow task.

### Postman API

Shows the API request used to trigger the Airflow DAG and the response received from Airflow.

## Expected Result

The project successfully demonstrates an API-triggered workflow using Apache Airflow running inside Docker.

The pizza order information is passed through the Airflow REST API, the DAG is triggered, and the configured tasks are executed as part of the pizza delivery pipeline.

## Author

Gagan Namdev

Apache Airflow - Pizza Delivery Pipeline Assignment