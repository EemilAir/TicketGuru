import { useState, useEffect } from 'react';
import { Modal, Button, Form, ListGroup } from 'react-bootstrap';
import { haeMaksutavat } from '../api/maksutavat';
import FormField from './FormField';

export default function PrintAllTicketsModal({ show, handleClose, tapahtuma, onSell }) {
    const [maksutavat, setMaksutavat] = useState([]);
    const [liput, setLiput] = useState([]);
    const [summa, setSumma] = useState(0);
    const [selectedLipputyyppiId, setSelectedLipputyyppiId] = useState("");
    const [myyntitapahtuma, setMyyntitapahtuma] = useState({
        maksutapaId: "",
        kayttajaId: 2,
        liput: [],
        tapahtumaId: tapahtuma.tapahtumaId
    });

    useEffect(() => {
        const fetchMaksutavat = async () => {
            try {
                const response = await haeMaksutavat();
                setMaksutavat(response);
    
                // Find the 'Ovella' payment method
                const ovellaOption = response.find(option => option.maksutapaNimi === 'Ovella');
                if (ovellaOption) {
                    setMyyntitapahtuma(prevState => ({
                        ...prevState,
                        maksutapaId: ovellaOption.maksutapaId
                    }));
                } else {
                    console.error("'Ovella' payment method not found in maksutavat.");
                }
            } catch (error) {
                console.error("Failed to fetch maksutavat:", error);
            }
        };
        fetchMaksutavat();
    }, []);

    useEffect(() => {
        if (selectedLipputyyppiId) {
            const selectedLipputyyppi = tapahtuma.lipputyypit.find(
                lipputyyppi => lipputyyppi.id.lipputyyppiId === selectedLipputyyppiId
            );
            if (selectedLipputyyppi) {
                const lippu = {
                    lipputyyppiId: selectedLipputyyppi.id.lipputyyppiId,
                    tapahtumaId: selectedLipputyyppi.id.tapahtumaId,
                    maara: tapahtuma.lippujaJaljella,
                    nimi: selectedLipputyyppi.nimi,
                    hinta: selectedLipputyyppi.hinta
                };
                setLiput([lippu]);
                setMyyntitapahtuma(prevState => ({ ...prevState, liput: [lippu] }));
            }
        } else {
            setLiput([]);
            setMyyntitapahtuma(prevState => ({ ...prevState, liput: [] }));
        }
    }, [selectedLipputyyppiId, tapahtuma]);

    useEffect(() => {
        let total = 0;
        liput.forEach(lippu => {
            total += lippu.hinta * lippu.maara;
        });
        setSumma(total);
    }, [liput]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (myyntitapahtuma.liput.length === 0) return;
        try {
            await onSell(myyntitapahtuma);
            handleClose();
        } catch (error) {
            console.error("Selling tickets failed:", error);
        }
    };

    return (
        <Modal show={show} onHide={handleClose} backdrop="static" keyboard={false}>
            <Modal.Header closeButton>
                <Modal.Title>Myy kaikki liput tapahtumaan {tapahtuma.nimi}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit}>
                    <Form.Group controlId="lipputyyppiSelect">
                        <Form.Label>Valitse lipputyyppi</Form.Label>
                        <Form.Control
                            as="select"
                            value={selectedLipputyyppiId}
                            onChange={(e) => setSelectedLipputyyppiId(parseInt(e.target.value))}
                            required
                        >
                            <option value="">Valitse lipputyyppi</option>
                            {tapahtuma.lipputyypit.map((lipputyyppi) => (
                                <option
                                    key={lipputyyppi.id.lipputyyppiId}
                                    value={lipputyyppi.id.lipputyyppiId}
                                >
                                    {lipputyyppi.nimi}
                                </option>
                            ))}
                        </Form.Control>
                    </Form.Group>
                    {liput.length > 0 && (
                        <>
                            <h5>Liput</h5>
                            <ListGroup className="mb-3">
                                {liput.map((lippu, index) => (
                                    <ListGroup.Item
                                        key={index}
                                        className="d-flex justify-content-between align-items-center"
                                    >
                                        {lippu.nimi} - {lippu.maara} kpl
                                        <span>{lippu.hinta} €</span>
                                    </ListGroup.Item>
                                ))}
                            </ListGroup>
                        </>
                    )}
                    <FormField
                        label="Maksutapa"
                        type="select"
                        id="maksutapaId"
                        name="maksutapaId"
                        value={myyntitapahtuma.maksutapaId}
                        options={maksutavat.filter(option => option.maksutapaNimi === 'Ovella')}
                        optionKey={(option) => option.maksutapaId}
                        optionValue={(option) => option.maksutapaId}
                        optionLabel={(option) => option.maksutapaNimi}
                        required
                        disabled
                    />
                    <p>Varattu summa: {summa} €</p>
                    <Button variant="primary" type="submit">Tulosta liput</Button>
                </Form>
            </Modal.Body>
        </Modal>
    );
}