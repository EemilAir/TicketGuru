import { useState, useEffect } from 'react';
import { Modal, Button, Form, InputGroup, ListGroup } from 'react-bootstrap';
import { haeMaksutavat } from '../api/maksutavat';
import FormField from './FormField';

export default function MyyntitapahtumaModal({ show, handleClose, tapahtuma, onSell }) {
    const [maksutavat, setMaksutavat] = useState([]);
    const [liput, setLiput] = useState(tapahtuma.lipputyypit.map(lipputyyppi => ({
        lipputyyppiId: lipputyyppi.id.lipputyyppiId,
        tapahtumaId: lipputyyppi.id.tapahtumaId,
        maara: 0,
        nimi: lipputyyppi.nimi,
        hinta: lipputyyppi.hinta
    })));
    const [summa, setSumma] = useState(0);
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
                setMyyntitapahtuma(prevState => ({ ...prevState, maksutapaId: parseInt(response[0].maksutapaId) }));
            } catch (error) {
                console.error("Failed to fetch maksutavat:", error);
            }
        };
        fetchMaksutavat();
    }, []);

    useEffect(() => {
        laskeSumma();
    }, [myyntitapahtuma.liput]);

    const laskeSumma = () => {
        let summa = 0;
        myyntitapahtuma.liput.forEach(lippu => {
            const lipputyyppi = liput.find(lt => lt.lipputyyppiId === lippu.lipputyyppiId && lt.tapahtumaId === lippu.tapahtumaId);
            if (lipputyyppi) {
                summa += lipputyyppi.hinta * lippu.maara;
            }
        });
        setSumma(summa);
    };

    const handleMaaraChange = (index, delta) => {
        const newLiput = [...liput];
        newLiput[index].maara = Math.max(0, newLiput[index].maara + delta);
        setLiput(newLiput);
    };

    const handleAddToCart = () => {
        const newLiput = liput.filter(lippu => lippu.maara > 0);
        const updatedLiput = [...myyntitapahtuma.liput];

        newLiput.forEach(newLippu => {
            const existingLippu = updatedLiput.find(lippu => lippu.lipputyyppiId === newLippu.lipputyyppiId && lippu.tapahtumaId === newLippu.tapahtumaId);
            if (existingLippu) {
                existingLippu.maara += newLippu.maara;
            } else {
                updatedLiput.push(newLippu);
            }
        });

        setMyyntitapahtuma(prevState => ({ ...prevState, liput: updatedLiput }));
        // Nollataan liput-tilan määrät
        setLiput(liput.map(lippu => ({ ...lippu, maara: 0 })));
    };

    const handleRemoveFromCart = (index) => {
        const newLiput = [...myyntitapahtuma.liput];
        newLiput.splice(index, 1);
        setMyyntitapahtuma(prevState => ({ ...prevState, liput: newLiput }));
    };

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
                <Modal.Title>Myy Lippuja {tapahtuma.nimi} tapahtumaan</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit}>
                    {liput.map((lippu, index) => (
                        <Form.Group key={index} className="mb-3">
                            <Form.Label>
                                {lippu.nimi} - {lippu.hinta} €
                            </Form.Label>
                            <InputGroup>
                                <Button variant="outline-secondary" onClick={() => handleMaaraChange(index, -1)}>-</Button>
                                <Form.Control type="number" value={lippu.maara} readOnly />
                                <Button variant="outline-secondary" onClick={() => handleMaaraChange(index, 1)}>+</Button>
                            </InputGroup>
                        </Form.Group>
                    ))}
                    <Button variant="secondary" className="mb-3" onClick={handleAddToCart}>Lisää ostoskoriin</Button>
                    <hr />
                    <h5>Ostoskorissa olevat liput</h5>
                    {myyntitapahtuma.liput.length > 0 ? (
                        <ListGroup className="mb-3">
                            {myyntitapahtuma.liput.map((lippu, index) => (
                                <ListGroup.Item key={index} className="d-flex justify-content-between align-items-center">
                                    {liput.find(lt => lt.lipputyyppiId === lippu.lipputyyppiId && lt.tapahtumaId === lippu.tapahtumaId)?.nimi} - {lippu.maara} kpl
                                    <span>{liput.find(lt => lt.lipputyyppiId === lippu.lipputyyppiId && lt.tapahtumaId === lippu.tapahtumaId)?.hinta * lippu.maara} €</span>
                                    <Button variant="danger" size="sm" onClick={() => handleRemoveFromCart(index)}>Poista</Button>
                                </ListGroup.Item>
                            ))}
                        </ListGroup>
                    ) : (
                        <p>Ostoskorissa ei ole lippuja.</p>
                    )}
                    <FormField
                        label="Maksutapa"
                        type="select"
                        id="maksutapaId"
                        name="maksutapaId"
                        value={myyntitapahtuma.maksutapaId}
                        onChange={(e) => setMyyntitapahtuma({ ...myyntitapahtuma, maksutapaId: e.target.value })}
                        options={maksutavat.filter(option => option.maksutapaNimi !== 'Ovella')}
                        optionKey={(option) => option.maksutapaId}
                        optionValue={(option) => option.maksutapaId}
                        optionLabel={(option) => option.maksutapaNimi}
                        required
                    />
                    <p>Summa {summa} €</p>
                    <Button variant="primary" type="submit">Vahvista myynti</Button>
                </Form>
            </Modal.Body>
        </Modal>
    );
}