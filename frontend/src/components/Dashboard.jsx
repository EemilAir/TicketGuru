import  { useAuth } from "./AuthContext";

const Dashboard = () => {

    const { logout } = useAuth();

    return (
        <div>
            <h1>Dashboard</h1>
            <p>Welcome to the Dashboard page</p>
            <button onClick={logout}>Logout</button>
        </div>
    );
}

export default Dashboard;