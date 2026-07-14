import { Navigate } from "react-router-dom";
import { getToken } from "../utils/auth";

function ProtectedRoute({

    children,

    allowedRoles = []

}) {

    const token = getToken();

    const user = JSON.parse(
        localStorage.getItem("user")
    );

    if (!token) {

        return (
            <Navigate
                to="/login"
                replace
            />
        );

    }

    if (

        allowedRoles.length > 0 &&

        !allowedRoles.includes(
            user?.role?.toLowerCase()
        )

    ) {

        return (
            <Navigate
                to="/dashboard"
                replace
            />
        );

    }

    return children;

}

export default ProtectedRoute;