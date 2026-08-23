package com.example.reimbursementportal.constant;

public final class AppConstants {

    private AppConstants() {
    }

    public static final String COMPANY_EMAIL_DOMAIN = "@company.com";

    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String EMAIL_MUST_BELONG_TO_COMPANY_DOMAIN = "Email must belong to company domain";

    public static final String USER_NOT_FOUND = "User not found";
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
    public static final String MANAGER_NOT_FOUND = "Manager not found";
    public static final String REVIEWER_NOT_FOUND = "Reviewer not found";
    public static final String CLAIM_NOT_FOUND = "Claim not found";
    public static final String ADMIN_REVIEWER_NOT_FOUND = "Admin reviewer not found";

    public static final String ONLY_EMPLOYEE_CAN_SUBMIT_CLAIM = "Only employee can submit claim";
    public static final String ONLY_EMPLOYEE_CAN_VIEW_CLAIMS = "Only employee can view submitted claims";
    public static final String ONLY_SUBMITTED_CLAIMS_CAN_BE_APPROVED = "Only submitted claims can be approved";
    public static final String ONLY_SUBMITTED_CLAIMS_CAN_BE_REJECTED = "Only submitted claims can be rejected";
    public static final String COMMENT_REQUIRED_FOR_REJECTION = "Comment is required for rejection";
    public static final String NOT_ALLOWED_TO_REVIEW_CLAIM = "You are not allowed to review this claim";

    public static final String ASSIGNED_USER_IS_NOT_MANAGER = "Assigned user is not a manager";
    public static final String SELECTED_USER_IS_NOT_MANAGER = "Selected user is not a manager";
    public static final String MANAGER_CAN_BE_ASSIGNED_ONLY_TO_EMPLOYEE = "Manager can be assigned only to an employee";

    public static final String INVALID_EMAIL_OR_PASSWORD = "Invalid email or password";
    public static final String LOGIN_SUCCESSFUL = "Login successful";
}