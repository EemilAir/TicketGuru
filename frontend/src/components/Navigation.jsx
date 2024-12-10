import { useState, useEffect } from 'react';
import { Link, useNavigate, useLocation } from 'react-router';
import {
    Navbar,
    Nav,
    NavDropdown,
    Container,
    Form,
    InputGroup,
    Button
} from 'react-bootstrap';
import { useAuth } from './AuthContext';
import { fetchMyyntitapahtuma } from '../api/myyntitapahtumat';
import {
    FaHome,
    FaCalendarAlt,
    FaList,
    FaPlus,
    FaTicketAlt
} from "react-icons/fa";

export default function Navigation() {
    const [myyntitapahtumaId, setMyyntitapahtumaId] = useState('');
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    const location = useLocation();
    const { logout, username } = useAuth();

    const handleSearch = async () => {
        if (myyntitapahtumaId) {
            try {
                const myyntitapahtuma = await fetchMyyntitapahtuma(myyntitapahtumaId);
                if (myyntitapahtuma) {
                    navigate(`/myyntitapahtumat/${myyntitapahtumaId}`);
                } else {
                    setError('Myyntitapahtumaa ei löydy annetulla ID:llä.');
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

    const isActive = (path) => {
        return location.pathname === path;
    };

    const isDropdownActive = (paths) => {
        return paths.some(path => isActive(path));
    };

    return (
        <Navbar bg="light" expand="lg" className="shadow-sm p-3 mb-5 bg-white rounded fixed-top">
            <Container>
                <Navbar.Brand className="fw-bold text-primary">
                    <FaTicketAlt /> TicketGuru
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarSupportedContent" />
                <Navbar.Collapse id="navbarSupportedContent">
                    <Nav className="me-auto">
                        <Nav.Link as={Link} to="/" className={`me-3 ${isActive('/') ? 'active-link' : ''}`}>
                            <FaHome /> Hallintapaneeli
                        </Nav.Link>
                        <NavDropdown
                            title={<><FaCalendarAlt /> Tapahtumat</>}
                            id="tapahtumat-dropdown"
                            className={`me-3 ${isDropdownActive(['/tapahtumat', '/tapahtumat/uusi']) ? 'active-link' : ''}`}
                        >
                            <NavDropdown.Item as={Link} to="/tapahtumat" className={isActive('/tapahtumat') ? 'active' : ''}>
                                <FaList /> Kaikki tapahtumat
                            </NavDropdown.Item>
                            <NavDropdown.Item as={Link} to="/tapahtumat/uusi" className={isActive('/tapahtumat/uusi') ? 'active' : ''}>
                                <FaPlus /> Uusi tapahtuma
                            </NavDropdown.Item>
                        </NavDropdown>
                        <NavDropdown
                            title={<><i className="fa fa-shopping-cart me-1"></i>Myyntitapahtumat</>}
                            id="myyntitapahtumat-dropdown"
                            className={`me-3 ${isActive('/myyntitapahtumat') ? 'active-link' : ''}`}
                        >
                            <NavDropdown.Item as={Link} to="/myyntitapahtumat" className={isActive('/myyntitapahtumat') ? 'active' : ''}>
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
                        <NavDropdown
                            title={<><FaTicketAlt /> Lipputyypit</>}
                            id="lipputyypit-dropdown"
                            className={`me-3 ${isDropdownActive(['/lipputyypit', '/lipputyypit/uusi']) ? 'active-link' : ''}`}
                        >
                            <NavDropdown.Item as={Link} to="/lipputyypit" className={isActive('/lipputyypit') ? 'active' : ''}>
                                <FaList /> Kaikki lipputyypit
                            </NavDropdown.Item>
                            <NavDropdown.Item as={Link} to="/lipputyypit/uusi" className={isActive('/lipputyypit/uusi') ? 'active' : ''}>
                                <FaPlus /> Uusi lipputyyppi
                            </NavDropdown.Item>
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