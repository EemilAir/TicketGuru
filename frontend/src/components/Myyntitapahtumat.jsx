import { useEffect, useState } from 'react';
import { Row, Col, Spinner } from 'react-bootstrap';
import Myyntitapahtuma from './Myyntitapahtuma';
import { fetchMyyntitapahtumat } from '../api/myyntitapahtumat';

export default function Myyntitapahtumat() {
    const [myyntitapahtumat, setMyyntitapahtumat] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const getMyyntitapahtumat = async () => {
            try {
                const response = await fetchMyyntitapahtumat();
                setMyyntitapahtumat(response);
            } catch (error) {
                console.error("Error fetching myyntitapahtumat:", error);
            } finally {
                setIsLoading(false);
            }
        };
        getMyyntitapahtumat();
    }, []);

    if (isLoading) {
        return <Spinner animation="border" />;
    }

    if (myyntitapahtumat.length === 0) {
        return <p>Ei myyntitapahtumia löytynyt</p>;
    }

    return (
        <Row>
            {myyntitapahtumat.map(myyntitapahtuma => (
                <Col lg={4} md={6} sm={12} className="mb-4 d-flex" key={myyntitapahtuma.myyntitapahtumaId}>
                    <Myyntitapahtuma myyntitapahtuma={myyntitapahtuma} showLiput={false} />
                </Col>
            ))}
        </Row>
    );
}