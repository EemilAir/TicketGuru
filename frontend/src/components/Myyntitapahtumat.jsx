import React, { useEffect, useState } from 'react';
import { fetchMyyntitapahtumat } from '../api/myyntitapahtumat';
import { Card, Spinner, Container, Row, Col, Badge, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router';
import { FaMoneyBillWave, FaCalendarAlt, FaUser, FaTicketAlt, FaUserTag } from 'react-icons/fa';
import { formatDate } from '../utils/formatDate';

export default function Myyntitapahtumat() {
    const navigate = useNavigate();

    const [myyntitapahtumat, setMyyntitapahtumat] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const getMyyntitapahtumat = async () => {
            try {
                const data = await fetchMyyntitapahtumat();
                setMyyntitapahtumat(data);
            } catch (error) {
                setError('Virhe haettaessa myyntitapahtumia.');
                console.error("Fetching myyntitapahtumat failed:", error);
            } finally {
                setIsLoading(false);
            }
        };

        getMyyntitapahtumat();
    }, []);

    if (isLoading) {
        return <Spinner animation="border" />;
    }

    if (error) {
        return <p>{error}</p>;
    }

    if (myyntitapahtumat.length === 0) {
        return <p>Ei myyntitapahtumia löytynyt</p>;
    }

    return (
        <Container className="mt-4">
            <Row>
                {myyntitapahtumat.map(myyntitapahtuma => {
                    const lippujenMaara = myyntitapahtuma.liput.reduce((sum, lippu) => sum + 1, 0);
                    return (
                        <Col lg={4} md={6} sm={12} className="mb-4 d-flex" key={myyntitapahtuma.myyntitapahtumaId}>
                            <Card className="h-100 shadow-sm border-0" style={{ border: '1px solid #ccc' }}>
                                <Card.Body className="d-flex flex-column">
                                    <Card.Title className="d-flex justify-content-between align-items-center mb-3" style={{ backgroundColor: '#f8f9fa', padding: '10px', borderRadius: '5px' }}>
                                        <span className="h5 fw-bold">Myyntitapahtuma: {myyntitapahtuma.myyntitapahtumaId}</span>
                                        <Badge bg="primary" pill>{myyntitapahtuma.summa} €</Badge>
                                    </Card.Title>
                                    <Card.Text className="mb-2">
                                        <FaMoneyBillWave className="me-2 text-success" />
                                        <strong>Maksutapa:</strong> {myyntitapahtuma.maksutapa}
                                    </Card.Text>
                                    <Card.Text className="mb-2">
                                        <FaCalendarAlt className="me-2 text-info" />
                                        <strong>Maksupäivämäärä:</strong> {formatDate(myyntitapahtuma.maksupvm)}
                                    </Card.Text>
                                    <Card.Text className="mb-2">
                                        <FaUser className="me-2 text-primary" />
                                        <strong>Käyttäjä ID:</strong> {myyntitapahtuma.kayttajaId}
                                    </Card.Text>
                                    <Card.Text className="mb-2">
                                        <FaUserTag className="me-2 text-danger" />
                                        <strong>Tapahtuma ID:</strong> {myyntitapahtuma.tapahtumaId}
                                    </Card.Text>
                                    <Card.Text className="mb-3">
                                        <FaTicketAlt className="me-2 text-warning" />
                                        <strong>Lippujen määrä:</strong> {lippujenMaara}
                                    </Card.Text>
                                    <Button variant="primary" className="mt-auto" onClick={() => navigate(`/myyntitapahtumat/${myyntitapahtuma.myyntitapahtumaId}`)}>
                                        Näytä myyntitapahtuma
                                    </Button>
                                </Card.Body>
                            </Card>
                        </Col>
                    );
                })}
            </Row>
        </Container>
    );
}