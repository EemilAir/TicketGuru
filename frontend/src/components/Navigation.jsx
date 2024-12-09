import {useState, useEffect} from 'react';
import { Link, useNavigate } from 'react-router';
import { useAuth } from './AuthContext';
import { Navbar, Nav, NavDropdown, Container, Form, InputGroup, Button } from 'react-bootstrap';
import { fetchMyyntitapahtuma } from '../api/myyntitapahtumat';

export default function Navigation() {
    const { logout, username } = useAuth();
    const navigate = useNavigate();
    const [error, setError] = useState(null);
    const [myyntitapahtumaId, setMyyntitapahtumaId] = useState('');

    const handleSearch = async () => {
        if (myyntitapahtumaId) {
            try {
                const myyntitapahtuma = await fetchMyyntitapahtuma(myyntitapahtumaId);
                if (myyntitapahtuma) {
                    navigate(`/myyntitapahtumat/${myyntitapahtumaId}`);
                } else {
                    setError(`Myyntitapahtumaa ei löydy ID:llä ${myyntitapahtumaId}.`);
                }
            } catch (err) {
                setError('Virhe haettaessa myyntitapahtumaa.');
            }
        }
    };

    useEffect(() => {
        if (error) {
            const timer = setTimeout(() => {
                setError(null);
            }, 4000);

            return () => clearTimeout(timer);
        }
    }, [error]);

    return (
        <Navbar bg="light" expand="md" className="shadow-sm p-3 mb-5 bg-white rounded fixed-top">
        <Container>
            <Navbar.Brand className="fw-bold text-primary">
                <i className="fa fa-ticket-alt me-2"></i>TicketGuru
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="navbarSupportedContent" />
            <Navbar.Collapse id="navbarSupportedContent">
                <Nav className="me-auto">
                    <Nav.Link as={Link} to="/" className="me-3 text-secondary">
                        <i className="fa fa-home me-1"></i>Hallintapaneeli
                    </Nav.Link>
                    <NavDropdown title={<><i className="fa fa-calendar-alt me-1"></i>Tapahtumat</>} id="tapahtumat-dropdown" className="me-3">
                        <NavDropdown.Item as={Link} to="/tapahtumat">
                            <i className="fa fa-list me-2"></i>Kaikki tapahtumat
                        </NavDropdown.Item>
                        <NavDropdown.Item as={Link} to="/tapahtumat/uusi">
                            <i className="fa fa-plus me-2"></i>Uusi tapahtuma
                        </NavDropdown.Item>
                    </NavDropdown>
                    <NavDropdown title={<><i className="fa fa-shopping-cart me-1"></i>Myyntitapahtumat</>} id="myyntitapahtumat-dropdown" className="me-3">
                        <NavDropdown.Item as={Link} to="/myyntitapahtumat">
                            <i className="fa fa-list me-2"></i>Kaikki myyntitapahtumat
                        </NavDropdown.Item>
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
                                <Button variant="secondary" onClick={handleSearch}>
                                    <i className="fa fa-search"></i> Hae
                                </Button>
                            </InputGroup>
                            {error && <Form.Text className="text-danger">{error}</Form.Text>}
                        </Form.Group>
                    </NavDropdown>
                </Nav>
                <Nav>
                    <NavDropdown title={<><i className="fa-regular fa-user me-1"></i>{username}</>} id="user-dropdown">
                        <NavDropdown.Item onClick={logout}>
                            <i className="fa fa-sign-out-alt me-2" style={{ color: "red" }}></i>Logout
                        </NavDropdown.Item>
                    </NavDropdown>
                </Nav>
            </Navbar.Collapse>
        </Container>
    </Navbar>
    );
}