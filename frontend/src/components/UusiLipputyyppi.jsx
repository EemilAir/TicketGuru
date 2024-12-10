import { useState, useEffect } from 'react';
import { Form, Button, Container, Alert } from 'react-bootstrap';
import { useNavigate } from 'react-router';
import { getLipputyypit, createLipputyyppi } from '../api/lipputyypit';

export default function UusiLipputyyppi() {
    const [nimi, setNimi] = useState('');
    const [kuvaus, setKuvaus] = useState('');
    const [lipputyypit, setLipputyypit] = useState([]);
    const [error, setError] = useState(null);
    const [isSubmitted, setIsSubmitted] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchLipputyypit = async () => {
            try {
                const response = await getLipputyypit();
                setLipputyypit(response);
            } catch (err) {
                console.error("Virhe lipputyyppejä haettaessa", err);
            }
        };
        fetchLipputyypit();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsSubmitted(true);
        const trimmedNimi = nimi.trim();
        const trimmedKuvaus = kuvaus.trim();

        if (lipputyypit.some(lipputyyppi => lipputyyppi.lipputyyppiNimi.toLowerCase() === trimmedNimi.toLowerCase())) {
            setError('Lipputyypin nimi on jo olemassa.');
            return;
        }

        try {
            await createLipputyyppi({ lipputyyppiNimi: trimmedNimi, kuvaus: trimmedKuvaus });
            navigate('/lipputyypit');
        } catch (err) {
            console.error("Virhe lipputyypin luomisessa", err);
            setError('Virhe lipputyypin luomisessa.');
        }
    };

    const getClassName = (value, type) => {
        const trimmedValue = value.trim();
        if (!isSubmitted) {
            return '';
        }
        if (trimmedValue === '') {
            return 'is-invalid';
        }
        if (type === 'nimi' && lipputyypit.some(lipputyyppi => lipputyyppi.lipputyyppiNimi.toLowerCase() === trimmedValue.toLowerCase())) {
            return 'is-invalid';
        }
        if (type === 'kuvaus' && lipputyypit.some(lipputyyppi => lipputyyppi.kuvaus.toLowerCase() === trimmedValue.toLowerCase())) {
            return 'is-invalid';
        }
        return 'is-valid';
    };

    return (
        <>
            {error && <Alert variant="danger">{error}</Alert>}
            <Form onSubmit={handleSubmit}>
                <Form.Group className="mb-3" controlId="nimi">
                    <Form.Label>Nimi</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Syötä lipputyypin nimi"
                        value={nimi}
                        onChange={(e) => setNimi(e.target.value)}
                        required
                        className={getClassName(nimi, 'nimi')}
                    />
                </Form.Group>
                <Form.Group className="mb-3" controlId="kuvaus">
                    <Form.Label>Kuvaus</Form.Label>
                    <Form.Control
                        as="textarea"
                        rows={3}
                        placeholder="Syötä lipputyypin kuvaus"
                        value={kuvaus}
                        onChange={(e) => setKuvaus(e.target.value)}
                        required
                        className={getClassName(kuvaus, 'kuvaus')}
                    />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Luo lipputyyppi
                </Button>
            </Form>
        </>
    );
}