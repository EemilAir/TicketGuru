import { Card, Button, Row, Col, Form, InputGroup } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router';
import { useState } from 'react';

export default function Dashboard() {
    const [myyntitapahtumaId, setMyyntitapahtumaId] = useState('');
    const navigate = useNavigate();

    const handleSearch = () => {
        if (myyntitapahtumaId) {
            navigate(`/myyntitapahtumat/${myyntitapahtumaId}`);
        }
    };

    return (
        <Row className="mb-4 d-flex align-items-stretch">
            <Col xs={12} md={6} lg={4}>
                <Card className="h-100">
                    <Card.Body className="d-flex flex-column">
                        <Card.Title>Tapahtumat</Card.Title>
                        <Card.Text>
                            Hallinnoi tapahtumia.
                        </Card.Text>
                        <Button variant="primary" as={Link} to="/tapahtumat" className="mb-2">Kaikki tapahtumat</Button>
                        <Button variant="secondary" as={Link} to="/tapahtumat/uusi">Uusi tapahtuma</Button>
                    </Card.Body>
                </Card>
            </Col>
            <Col xs={12} md={6} lg={4}>
                <Card className="h-100">
                    <Card.Body className="d-flex flex-column">
                        <Card.Title>Myyntitapahtumat</Card.Title>
                        <Card.Text>
                            Hallinnoi myyntitapahtumia.
                        </Card.Text>
                        <Button variant="primary" as={Link} to="/myyntitapahtumat" className="mb-2">Kaikki myyntitapahtumat</Button>
                        <Form.Group className="mt-2">
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
                    </Card.Body>
                </Card>
            </Col>
            <Col xs={12} md={6} lg={4}>
                <Card className="h-100">
                    <Card.Body className="d-flex flex-column">
                        <Card.Title>Lipputyypit</Card.Title>
                        <Card.Text>
                            Hallinnoi lipputyyppejä.
                        </Card.Text>
                        <Button variant="primary" as={Link} to="/lipputyypit/uusi">Uusi lipputyyppi</Button>
                    </Card.Body>
                </Card>
            </Col>
        </Row>
    );
}