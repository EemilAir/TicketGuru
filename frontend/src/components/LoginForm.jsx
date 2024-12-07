import { useState } from 'react';
import { useAuth } from './AuthContext';
import { Form, Button, Card } from 'react-bootstrap';

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const { login } = useAuth();

    const handleLogin = (e) => {
        e.preventDefault();
        login(username, password);
    };

    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            <Card style={{ width: "20rem" }}>
                <Card.Body>
                    <Card.Title className="text-center mb-4">Login</Card.Title>
                    <Form onSubmit={handleLogin}>
                        <Form.Group className="mb-3" controlId="username">
                            <Form.Label>Username</Form.Label>
                            <Form.Control
                                type="text"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                required
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="password">
                            <Form.Label>Password</Form.Label>
                            <Form.Control
                                type="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </Form.Group>
                        <Button variant="primary" type="submit" className="w-100">Login</Button>
                    </Form>
                </Card.Body>
            </Card>
        </div>
    );
};

export default LoginForm;