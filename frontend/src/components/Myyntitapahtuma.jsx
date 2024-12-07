import { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { Card, Spinner } from 'react-bootstrap';
import Liput from './Liput';
import { fetchMyyntitapahtuma } from '../api/myyntitapahtumat';
import { formatDate } from '../utils/formatDate';
import { updateLipunTila } from '../api/liput';

export default function Myyntitapahtuma() {
    const { id } = useParams();
    const [myyntitapahtuma, setMyyntitapahtuma] = useState({});
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const getMyyntitapahtuma = async () => {
            try {
                const response = await fetchMyyntitapahtuma(id);
                setMyyntitapahtuma(response);
            } catch (error) {
                console.error("Error fetching myyntitapahtuma:", error);
            } finally {
                setIsLoading(false);
            }
        };
        getMyyntitapahtuma();
    }, [id]);

    const handleLipunTila = async (koodi, tila) => {
        try {
            const response = await updateLipunTila(koodi, tila);
            setMyyntitapahtuma((prev) => ({
                ...prev,
                liput: prev.liput.map(lippu => lippu.koodi === koodi ? response : lippu)
            }));
        } catch (error) {
            console.error("Failed to update ticket:", error);
        }
    };

    return (
        <>
            {isLoading && <Spinner animation="border" />}
            {!isLoading && myyntitapahtuma &&
                <Card>
                    <Card.Header>
                        <h1>Myyntitapahtuma: {myyntitapahtuma.myyntitapahtumaId}</h1>
                    </Card.Header>
                    <Card.Body>
                        <p><strong>Summa:</strong> {myyntitapahtuma.summa} €</p>
                        <p><strong>Maksutapa:</strong> {myyntitapahtuma.maksutapa}</p>
                        <p><strong>Maksupäivämäärä:</strong> {formatDate(myyntitapahtuma.maksupvm)}</p>
                        <p><strong>Käyttäjä ID:</strong> {myyntitapahtuma.kayttajaId}</p>
                        <p><strong>Tapahtuma ID:</strong> {myyntitapahtuma.tapahtumaId}</p>
                        <Liput liput={myyntitapahtuma.liput} handleLipunTila={handleLipunTila} />
                    </Card.Body>
                </Card>
            }
        </>
    );
}