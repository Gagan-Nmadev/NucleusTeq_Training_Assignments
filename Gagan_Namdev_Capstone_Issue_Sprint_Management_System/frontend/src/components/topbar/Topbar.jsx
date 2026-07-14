import "./Topbar.css";

function Topbar() {

    const user = JSON.parse(
        localStorage.getItem("user")
    );

    const role = user?.role?.toLowerCase();

    const roleLabel = {
        admin: "ADMIN",
        member: "MEMBER",
        viewer: "VIEWER",
    };

    return (

        <header className="topbar">

            <div className="topbar-left">

                <h2>
                    Dashboard
                </h2>

                <p className="welcome-text">
                    Welcome back, <strong>{user?.name}</strong>
                </p>

            </div>

            <div className="topbar-right">

                <div className="user-box">

                    <div className="avatar">

                        {
                            user?.name
                                ?.charAt(0)
                                .toUpperCase()
                        }

                    </div>

                    <div className="user-info">

                        <h4>
                            {user?.name}
                        </h4>

                        <span
                            className={`role-badge ${role}`}
                        >
                            {roleLabel[role]}
                        </span>

                    </div>

                </div>

            </div>

        </header>

    );

}

export default Topbar;