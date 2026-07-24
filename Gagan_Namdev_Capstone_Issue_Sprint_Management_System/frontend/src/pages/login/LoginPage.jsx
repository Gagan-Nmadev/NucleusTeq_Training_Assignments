import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import toast from "react-hot-toast";

import "./LoginPage.css";

import {
    loginUser,
    getProfile,
} from "../../services/auth-service";

function LoginPage() {

    const navigate = useNavigate();

    const [loading, setLoading] = useState(false);

    const [showPassword, setShowPassword] = useState(false);

    const [formData, setFormData] = useState({
        email: "",
        password: "",
    });

    const handleChange = (e) => {
        setFormData((prev) => ({
            ...prev,
            [e.target.name]: e.target.value,
        }));
    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        if (!formData.email.trim()) {
            toast.error("Email is required");
            return;
        }

        if (!formData.password.trim()) {
            toast.error("Password is required");
            return;
        }

        try {

            setLoading(true);

            // Login API
            const loginResponse = await loginUser(formData);

            // Save JWT Token
            localStorage.setItem(
                "token",
                loginResponse.access_token
            );

            // Fetch Logged In User
            const profile = await getProfile();

            // Save User Details
            localStorage.setItem(
                "user",
                JSON.stringify(profile)
            );

            toast.success("Login Successful");

            navigate("/dashboard");

        } catch (error) {

            toast.error(
                error?.response?.data?.detail ||
                error?.response?.data?.message ||
                "Invalid Email or Password"
            );

        } finally {

            setLoading(false);

        }

    };

    return (

        <div className="login-container">

            <div className="login-card">

                <h1>Issue & Sprint Management</h1>

                <p>Login to Continue</p>

                <form onSubmit={handleSubmit}>

                    <div className="form-group">

                        <label>Email</label>

                        <input
                            type="email"
                            name="email"
                            placeholder="Enter Email"
                            value={formData.email}
                            onChange={handleChange}
                        />

                    </div>

                    <div className="form-group">

                        <label>Password</label>

                        <input
                            type={showPassword ? "text" : "password"}
                            name="password"
                            placeholder="Enter Password"
                            value={formData.password}
                            onChange={handleChange}
                        />

                    </div>

                    <div className="checkbox">

                        <input
                            type="checkbox"
                            checked={showPassword}
                            onChange={() =>
                                setShowPassword((prev) => !prev)
                            }
                        />

                        <span>Show Password</span>

                    </div>

                    <button
                        type="submit"
                        className="login-btn"
                        disabled={loading}
                    >
                        {loading ? "Logging in..." : "Login"}
                    </button>

                </form>

                <div className="bottom-text">

                    Don't have an account?

                    <Link to="/register">
                        Register
                    </Link>

                </div>

            </div>

        </div>

    );
}

export default LoginPage;