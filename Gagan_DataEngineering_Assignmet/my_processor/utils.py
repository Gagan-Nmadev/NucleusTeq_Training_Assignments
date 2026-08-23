import re


class InvalidMemberDataError(Exception):
    """Custom exception for invalid member data."""
    pass


def clean_string(value):
    """Remove unnecessary spaces from a string."""
    if not isinstance(value, str):
        raise InvalidMemberDataError("Value must be a string.")

    return value.strip()


def validate_email(email):
    """Validate email using regular expression."""

    email_pattern = r"^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$"

    return re.match(email_pattern, email) is not None


def validate_phone(phone):
    """Validate phone number using regular expression."""

    phone_pattern = r"^\d{3}-\d{4}$"

    return re.match(phone_pattern, phone) is not None


def clean_member_data(data):
    """Clean and validate raw member dictionary."""

    required_fields = ["name", "email", "phone"]

    for field in required_fields:
        if field not in data:
            raise InvalidMemberDataError(
                f"Missing required field: {field}"
            )

    name = clean_string(data["name"])
    email = clean_string(data["email"])
    phone = clean_string(data["phone"])

    # Slicing example
    name = name[:50]

    if not name:
        raise InvalidMemberDataError("Name cannot be empty.")

    if not validate_email(email):
        raise InvalidMemberDataError(
            f"Invalid email for member '{name}'."
        )

    if not validate_phone(phone):
        raise InvalidMemberDataError(
            f"Invalid phone for member '{name}'."
        )

    return {
        "name": name,
        "email": email,
        "phone": phone
    }