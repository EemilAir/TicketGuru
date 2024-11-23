import { useAuth } from "./AuthContext";

const Dashboard = () => {

    const { logout, username } = useAuth();

    return (
        <div className="container mt-4">
            <div className="row align-items-center">
                <div className="col">
                    <h1>Dashboard</h1>
                </div>
                <div className="col text-end">
                    <div className="dropdown">
                        <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                            {username}
                        </button>
                        <ul className="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton">
                            <li>
                                <button className="dropdown-item" onClick={logout}>
                                <i className="fa fa-sign-out" aria-hidden="true" style={{color: "red"}}></i> Logout
                                </button>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Dashboard;