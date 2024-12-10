import { useState, useEffect, useRef } from 'react';
import { useAuth } from './AuthContext';
import { Form, Button, Card, InputGroup } from 'react-bootstrap';

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const usernameRef = useRef(null);
    const [error, setError] = useState(null); 
    
    const { login } = useAuth();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            await login(username, password);
        } catch (err) {
            setError('Käyttäjänimi tai salasana on väärin.');
            setUsername('');
            setPassword('');
            usernameRef.current.focus();
        }
    };

    useEffect(() => {
        if (error) {
            const timer = setTimeout(() => {
                setError(null);
            }, 5000); 

            return () => clearTimeout(timer); 
        }
    }, [error]);

    return (
        <div className="d-flex justify-content-center align-items-center vh-100 bg-light">
            <Card className="shadow-lg p-3 mb-5 bg-white rounded" style={{ width: "20rem" }}>
                <Card.Body>
                    <Card.Title className="text-center fw-bold text-primary">
                    <i className="fa fa-ticket-alt me-2"></i>TicketGuru
                    </Card.Title>
                    <Card.Title className="text-center mb-4">Login</Card.Title>
                    <Form onSubmit={handleLogin}>
                        <Form.Group className="mb-3" controlId="username">
                            <Form.Label>Username</Form.Label>
                            <InputGroup>
                                <InputGroup.Text>
                                    <i className="fa fa-user"></i>
                                </InputGroup.Text>
                                <Form.Control
                                    type="text"
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}
                                    ref={usernameRef}
                                    placeholder="Enter username"
                                    required
                                />
                            </InputGroup>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="password">
                            <Form.Label>Password</Form.Label>
                            <InputGroup>
                                <InputGroup.Text>
                                    <i className="fa fa-lock"></i>
                                </InputGroup.Text>
                                <Form.Control
                                    type="password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    placeholder="Enter password"
                                    required
                                />
                            </InputGroup>
                        </Form.Group>
                        {error && <div className="text-danger mb-3">{error}</div>}
                        <Button variant="primary" type="submit" className="w-100">
                            <i className="fa fa-sign-in-alt"></i> Login
                        </Button>
                    </Form>
                </Card.Body>
            </Card>
        </div>
    );
};

export default LoginForm;