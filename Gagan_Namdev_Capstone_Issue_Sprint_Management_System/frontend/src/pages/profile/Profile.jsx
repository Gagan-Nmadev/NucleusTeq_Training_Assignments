import { useState } from "react";
import "./Profile.css";

function Profile() {

    const [user] = useState(
        JSON.parse(localStorage.getItem("user")) || {
            full_name: "Admin User",
            username: "admin",
            email: "admin@gmail.com",
            role: "ADMIN",
            status: "Active",
        }
    );

    return (

        <div className="profile-page">

            <div className="profile-card">

                <div className="profile-header">

                    <div className="profile-avatar">

                        {user.username?.charAt(0).toUpperCase()}

                    </div>

                    <h2>{user.full_name}</h2>

                    <span className="profile-role">

                        {user.role}

                    </span>

                </div>

                <div className="profile-body">


                    <div className="profile-row">

                        <span>Email</span>

                        <p>{user.email}</p>

                    </div>

                    <div className="profile-row">

                        <span>Role</span>

                        <p>{user.role}</p>

                    </div>

                    <div className="profile-row">

                        <span>Status</span>

                        <p className="status-active">

                            {user.status || "Active"}

                        </p>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default Profile;