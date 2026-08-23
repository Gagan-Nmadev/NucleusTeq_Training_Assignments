import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import toast from "react-hot-toast";

import "./RegisterPage.css";
import { registerUser } from "../../services/auth-service";

function RegisterPage() {
    const navigate = useNavigate();

    const [loading, setLoading] = useState(false);

    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);

    const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    role: "member",
});
    const handleChange = (e) => {
        setFormData((prev) => ({
            ...prev,
            [e.target.name]: e.target.value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!formData.name.trim()) {
            toast.error("Name is required");
            return;
        }

        if (!formData.email.trim()) {
            toast.error("Email is required");
            return;
        }

        if (!formData.password.trim()) {
            toast.error("Password is required");
            return;
        }

        if (formData.password.length < 6) {
            toast.error("Password must be at least 6 characters");
            return;
        }

        if (formData.password !== formData.confirmPassword) {
            toast.error("Passwords do not match");
            return;
        }

        try {
            setLoading(true);

            const response = await registerUser({
                name: formData.name,
                email: formData.email,
                password: formData.password,
                role: formData.role,
            });

            toast.success(response.message);

            setTimeout(() => {
                navigate("/login");
            }, 1200);

        } catch (err) {

            toast.error(
                err.response?.data?.detail ||
                err.response?.data?.message ||
                "Registration Failed"
            );

        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="register-page">

            <div className="register-card">

                <div className="register-header">

                    <div className="logo-circle">
                        👤
                    </div>

                    <div>
                        <h1>Create Account</h1>
                        <p>Join Issue & Sprint Management System</p>
                    </div>

                </div>

                <form onSubmit={handleSubmit}>

                    <div className="form-group">
                        <label>Full Name</label>

                        <input
                            type="text"
                            name="name"
                            placeholder="Enter your full name"
                            value={formData.name}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>Email Address</label>

                        <input
                            type="email"
                            name="email"
                            placeholder="Enter your email"
                            value={formData.email}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-group">

                        <label>Password</label>

                        <div className="password-box">

                            <input
                                type={showPassword ? "text" : "password"}
                                name="password"
                                placeholder="Enter Password"
                                value={formData.password}
                                onChange={handleChange}
                            />

                            <button
                                type="button"
                                className="eye-btn"
                                onClick={() =>
                                    setShowPassword(!showPassword)
                                }
                            >
                                {showPassword ? "🙈" : "👁"}
                            </button>

                        </div>

                    </div>

                    <div className="form-group">

                        <label>Confirm Password</label>

                        <div className="password-box">

                            <input
                                type={
                                    showConfirmPassword
                                        ? "text"
                                        : "password"
                                }
                                name="confirmPassword"
                                placeholder="Confirm Password"
                                value={formData.confirmPassword}
                                onChange={handleChange}
                            />

                            <button
                                type="button"
                                className="eye-btn"
                                onClick={() =>
                                    setShowConfirmPassword(
                                        !showConfirmPassword
                                    )
                                }
                            >
                                {showConfirmPassword ? "🙈" : "👁"}
                            </button>

                        </div>

                    </div>

                    <div className="form-group">

                        <label>Role</label>

                        <select
    name="role"
    value={formData.role}
    onChange={handleChange}
>
    <option value="member">Member</option>
    <option value="viewer">Viewer</option>
</select>
                    </div>

                    <button
                        className="register-btn"
                        disabled={loading}
                    >
                        {loading ? "Registering..." : "Register"}
                    </button>

                </form>

                <div className="login-link">

                    Already have an account?

                    <Link to="/login">
                        Login
                    </Link>

                </div>

            </div>

        </div>
    );
}

export default RegisterPage;