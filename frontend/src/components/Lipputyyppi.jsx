import { useState } from 'react';
import { Button, Card, Form } from 'react-bootstrap';
import { FaEdit } from 'react-icons/fa';

export default function Lipputyyppi({ lipputyyppi, updateLipputyyppi, deleteLipputyyppi }) {
    const [nimi, setNimi] = useState(lipputyyppi.lipputyyppiNimi);
    const [kuvaus, setKuvaus] = useState(lipputyyppi.kuvaus);
    const [isModified, setIsModified] = useState(false);
    const [isEditingNimi, setIsEditingNimi] = useState(false);
    const [isEditingKuvaus, setIsEditingKuvaus] = useState(false);
    const [confirmDelete, setConfirmDelete] = useState(false);

    const handleNimiChange = (e) => {
        const trimmedNimi = e.target.value.trim();
        setNimi(e.target.value);
        setIsModified(trimmedNimi !== lipputyyppi.lipputyyppiNimi.trim() || kuvaus.trim() !== lipputyyppi.kuvaus.trim());
    };

    const handleKuvausChange = (e) => {
        const trimmedKuvaus = e.target.value.trim();
        setKuvaus(e.target.value);
        setIsModified(nimi.trim() !== lipputyyppi.lipputyyppiNimi.trim() || trimmedKuvaus !== lipputyyppi.kuvaus.trim());
    };

    const handleSave = () => {
        updateLipputyyppi(lipputyyppi.id, { lipputyyppiNimi: nimi.trim(), kuvaus: kuvaus.trim() });
        setIsModified(false);
        setIsEditingNimi(false);
        setIsEditingKuvaus(false);
    };

    const handleDelete = () => {
        deleteLipputyyppi(lipputyyppi.id);
    };

    return (
        <Card className="h-100 d-flex flex-column">
            <Card.Header>
                <Card.Title>ID: {lipputyyppi.id}</Card.Title>
            </Card.Header>
            <Card.Body className="d-flex flex-column">
                <Form.Group className="mb-3">
                    <Form.Label>Nimi</Form.Label>
                    {isEditingNimi ? (
                        <Form.Control
                            type="text"
                            value={nimi}
                            onChange={handleNimiChange}
                            onBlur={() => setIsEditingNimi(false)}
                            autoFocus
                        />
                    ) : (
                        <div onClick={() => setIsEditingNimi(true)}><FaEdit style={{cursor: 'pointer'}}/> {nimi}</div>
                    )}
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Kuvaus</Form.Label>
                    {isEditingKuvaus ? (
                        <Form.Control
                            type="text"
                            value={kuvaus}
                            onChange={handleKuvausChange}
                            onBlur={() => setIsEditingKuvaus(false)}
                            autoFocus
                        />
                    ) : (
                        <div onClick={() => setIsEditingKuvaus(true)}><FaEdit style={{cursor: 'pointer'}}/> {kuvaus}</div>
                    )}
                </Form.Group>
                <div className="mt-auto">
                    {isModified && (
                        <Button variant="success" className="w-100 mb-2" onClick={handleSave}>
                            Tallenna
                        </Button>
                    )}
                    {confirmDelete ? (
                        <>
                            <Button variant="danger" className="w-100 mb-2" onClick={handleDelete}>
                                Vahvista poisto
                            </Button>
                            <Button variant="secondary" className="w-100" onClick={() => setConfirmDelete(false)}>
                                Peruuta
                            </Button>
                        </>
                    ) : (
                        <Button variant="danger" className="w-100" onClick={() => setConfirmDelete(true)}>
                            Poista
                        </Button>
                    )}
                </div>
            </Card.Body>
        </Card>
    );
}