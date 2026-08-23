
from datetime import datetime
from airflow import DAG
from airflow.operators.python import PythonOperator, BranchPythonOperator
from airflow.operators.bash import BashOperator
from airflow.utils.log.logging_mixin import LoggingMixin

OWNER_NAME = "Gagan Namdev"
PIZZA_NAME = "Gagan's Special"

DEFAULT_ARGS = {
    "owner":OWNER_NAME,
}


def receive_order(**context):
    """Receive a new pizza order."""
    logger = LoggingMixin().log
    order_id = f"GD-{context['run_id'][-8:]}"

    logger.info(
        "Order received successfully. Order ID: %s | Owner: %s",
        order_id,
        OWNER_NAME,
    )
    logger.debug(
        "Pizza selected: %s",
        PIZZA_NAME,
    )

    context["ti"].xcom_push(
        key="order_id",
        value=order_id,
    )
    logger.info(
        "Order ID %s stored in XCom.",
        order_id,
    )


def prepare_order(**context):
    """Prepare the pizza order."""
    logger = LoggingMixin().log
    order_id = context["ti"].xcom_pull(
        task_ids="receive_order",
        key="order_id",
    )

    if not order_id:
        logger.critical(
            "Order preparation failed. Order ID missing from XCom."
        )
        raise ValueError("Order ID missing.")

    logger.info(
        "Preparing pizza order %s.",
        order_id,
    )
    logger.debug(
        "Dough preparation and ingredient verification started."
    )
    logger.info(
        "Order %s is ready for topping verification.",
        order_id,
    )


def check_toppings(**context):
    """Check topping inventory."""
    logger = LoggingMixin().log
    order_id = context["ti"].xcom_pull(
        task_ids="receive_order",
        key="order_id",
    )

    # False is intentional so that the skipped branch
    # can be demonstrated for the assignment.
    topping_available = True

    logger.info(
        "Checking topping inventory for order %s.",
        order_id,
    )

    if topping_available:
        logger.info(
            "All required toppings are available."
        )
        context["ti"].xcom_push(
            key="topping_status",
            value="available",
        )
    else:
        logger.warning(
            "Required topping is OUT OF STOCK for order %s.",
            order_id,
        )
        context["ti"].xcom_push(
            key="topping_status",
            value="out_of_stock",
        )


def choose_topping_path(**context):
    """Select the next task based on topping availability."""
    logger = LoggingMixin().log
    topping_status = context["ti"].xcom_pull(
        task_ids="check_toppings",
        key="topping_status",
    )

    logger.info(
        "Topping status received from XCom: %s",
        topping_status,
    )

    if topping_status == "available":
        logger.info(
            "Toppings available. Sending order to baking."
        )
        return "bake_pizza"

    logger.warning(
        "Toppings unavailable. Baking task will be skipped."
    )
    return "skip_out_of_stock"


def handle_out_of_stock(**context):
    """Handle an unavailable topping."""
    logger = LoggingMixin().log
    order_id = context["ti"].xcom_pull(
        task_ids="receive_order",
        key="order_id",
    )

    logger.warning(
        "Order %s cannot continue because a required "
        "topping is out of stock.",
        order_id,
    )
    logger.info(
        "Baking was deliberately skipped to avoid "
        "producing an incomplete pizza."
    )


def quality_check(**context):
    """Perform final pizza quality check."""
    logger = LoggingMixin().log
    order_id = context["ti"].xcom_pull(
        task_ids="receive_order",
        key="order_id",
    )

    logger.info(
        "Starting quality check for order %s.",
        order_id,
    )
    logger.debug(
        "Checking bake quality, pizza temperature, "
        "and packaging readiness."
    )
    logger.info(
        "Quality check passed for order %s.",
        order_id,
    )


def dispatch_pizza(**context):
    """Dispatch the completed pizza."""
    logger = LoggingMixin().log
    order_id = context["ti"].xcom_pull(
        task_ids="receive_order",
        key="order_id",
    )

    logger.info(
        "Pizza order %s has been packed.",
        order_id,
    )
    logger.info(
        "Order %s handed to delivery partner.",
        order_id,
    )


# DAG Definition
with DAG(
    dag_id="gagan_pizza_delivery_pipeline",
    description=("Gagan Namdev's automated pizza delivery pipeline"),
    default_args=DEFAULT_ARGS,
    start_date=datetime(2026, 8, 1),
    schedule="0 12,19 * * *",
    catchup=False,
    tags=["pizza", "assignment", "gagan-namdev"],
) as dag:

    receive_order_task = PythonOperator(
        task_id="receive_order",
        python_callable=receive_order,
    )

    prepare_order_task = PythonOperator(
        task_id="prepare_order",
        python_callable=prepare_order,
    )

    check_toppings_task = PythonOperator(
        task_id="check_toppings",
        python_callable=check_toppings,
    )

    topping_branch_task = BranchPythonOperator(
        task_id="handle_topping_status",
        python_callable=choose_topping_path,
    )

    skip_out_of_stock_task = PythonOperator(
        task_id="skip_out_of_stock",
        python_callable=handle_out_of_stock,
    )

    bake_pizza_task = BashOperator(
        task_id="bake_pizza",
        bash_command="""
        echo "Gagan Namdev pizza oven started"
        sleep 5
        echo "Pizza baking completed successfully"
        """,
    )

    quality_check_task = PythonOperator(
        task_id="quality_check",
        python_callable=quality_check,
    )

    dispatch_pizza_task = PythonOperator(
        task_id="dispatch_pizza",
        python_callable=dispatch_pizza,
    )

    # Dependencies (Task Flow)
    receive_order_task >> prepare_order_task
    prepare_order_task >> check_toppings_task
    check_toppings_task >> topping_branch_task

    topping_branch_task >> bake_pizza_task
    topping_branch_task >> skip_out_of_stock_task

    bake_pizza_task >> quality_check_task
    quality_check_task >> dispatch_pizza_task
