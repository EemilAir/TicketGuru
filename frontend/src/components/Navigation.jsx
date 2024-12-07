import { Link } from 'react-router';
import { useAuth } from './AuthContext';
import { Navbar, Nav, NavDropdown, Container } from 'react-bootstrap';

export default function Navigation() {
    const { logout, username } = useAuth();

    return (
        <Navbar bg="light" expand="md">
            <Container>
                <Navbar.Brand as={Link} to="/">Dashboard</Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarSupportedContent" />
                <Navbar.Collapse id="navbarSupportedContent">
                    <Nav className="me-auto">
                        <NavDropdown title="Tapahtumat" id="tapahtumat-dropdown">
                            <NavDropdown.Item as={Link} to="/tapahtumat">Kaikki tapahtumat</NavDropdown.Item>
                            <NavDropdown.Item as={Link} to="/tapahtumat/uusi">Uusi tapahtuma</NavDropdown.Item>
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