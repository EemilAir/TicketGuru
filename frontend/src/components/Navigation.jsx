import {useState} from 'react';
import { Link, useNavigate } from 'react-router';
import { useAuth } from './AuthContext';
import { Navbar, Nav, NavDropdown, Container, Form, InputGroup, Button } from 'react-bootstrap';

export default function Navigation() {
    const { logout, username } = useAuth();
    const navigate = useNavigate();
    const [myyntitapahtumaId, setMyyntitapahtumaId] = useState('');

    const handleSearch = () => {
        if (myyntitapahtumaId) {
            navigate(`/myyntitapahtumat/${myyntitapahtumaId}`);
        }
    };

    return (
        <Navbar bg="light" expand="md">
            <Container>
                <Navbar.Toggle aria-controls="navbarSupportedContent" />
                <Navbar.Collapse id="navbarSupportedContent">
                    <Nav.Link as={Link} to="/" className="my-2">Hallintapaneeli</Nav.Link>
                    <Nav>
                        <NavDropdown title="Tapahtumat" id="tapahtumat-dropdown">
                            <NavDropdown.Item as={Link} to="/tapahtumat">Kaikki tapahtumat</NavDropdown.Item>
                            <NavDropdown.Item as={Link} to="/tapahtumat/uusi">Uusi tapahtuma</NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                    <Nav className='me-auto'>
                        <NavDropdown title="Myyntitapahtumat" id="myyntitapahtumat-dropdown">
                            <NavDropdown.Item as={Link} to="/myyntitapahtumat">Kaikki myyntitapahtumat</NavDropdown.Item>
                            <Form.Group className="mt-2 px-3">
                                <InputGroup>
                                    <Form.FloatingLabel controlId="floatingInput" label="Haku ID:llä">
                                        <Form.Control
                                            type="text"
                                            placeholder="Haku myyntitapahtuma ID:llä"
                                            value={myyntitapahtumaId}
                                            onChange={(e) => setMyyntitapahtumaId(e.target.value)}
                                        />
                                    </Form.FloatingLabel>
                                    <Button variant="secondary" onClick={handleSearch}>Hae</Button>
                                </InputGroup>
                            </Form.Group>
                        </NavDropdown>
                    </Nav>
                    <Nav>
                        <NavDropdown title={<><i className="fa-regular fa-user"></i> {username}</>} id="user-dropdown">
                            <NavDropdown.Item onClick={logout}>
                                <i className="fa fa-sign-out" aria-hidden="true" style={{ color: "red" }}></i> Logout
                            </NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}