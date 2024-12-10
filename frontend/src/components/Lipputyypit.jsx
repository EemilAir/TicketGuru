import { useState, useEffect } from 'react';
import { Col, Row, Spinner } from 'react-bootstrap';
import Lipputyyppi from './Lipputyyppi';

import { getLipputyypit, updateLipputyyppi, deleteLipputyyppi } from '../api/lipputyypit';

export default function Lipputyypit() {
    const [lipputyypit, setLipputyypit] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const fetchLipputyypit = async () => {
            try {
                const response = await getLipputyypit();
                setLipputyypit(response);
            } catch (err) {
                console.error("Virhe lipputyyppejä haettaessa", err);
            } finally {
                setIsLoading(false);
            }
        };
        fetchLipputyypit();
    }, []);

    const handleUpdate = async (id, updatedLipputyyppi) => {
        try {
            const response = await updateLipputyyppi(id, updatedLipputyyppi);
            setLipputyypit(lipputyypit.map((lipputyyppi) => lipputyyppi.id === id ? response : lipputyyppi));
        } catch (error) {
            console.error("Virhe lipputyypin päivittämisessä", error);
        }
    }

    const handleDelete = async (id) => {
        try {
            await deleteLipputyyppi(id);
            setLipputyypit(lipputyypit.filter((lipputyyppi) => lipputyyppi.id !== id));
        } catch (error) {
            console.error("Virhe lipputyypin poistamisessa", error);
        }
    }

    return (
        <>
            {isLoading && <Spinner animation="border" role="status" />}
            {!isLoading && (
                <Row>
                    {lipputyypit?.map((lipputyyppi) => (
                        <Col key={lipputyyppi.id} xs={12} md={6} lg={4} className='mb-3'>
                            <Lipputyyppi lipputyyppi={lipputyyppi} updateLipputyyppi={handleUpdate} deleteLipputyyppi={handleDelete} />
                        </Col>
                    ))}
                </Row>
            )}
        </>
    )
}