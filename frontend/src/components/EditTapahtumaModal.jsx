import { useState } from 'react';
import FormField from './FormField';
import { Modal, Button, Form } from 'react-bootstrap';

export default function EditTapahtumaModal({ show, handleClose, tapahtuma, onEdit }) {
    const [nimi, setNimi] = useState(tapahtuma.nimi);
    const [kuvaus, setKuvaus] = useState(tapahtuma.kuvaus);
    const [kategoria, setKategoria] = useState(tapahtuma.kategoria);
    const [aloituspvm, setAloituspvm] = useState(tapahtuma.aloituspvm);
    const [lopetuspvm, setLopetuspvm] = useState(tapahtuma.lopetuspvm);
    const [lippujaJaljella, setLippujaJaljella] = useState(tapahtuma.lippujaJaljella);

    const handleSubmit = (e) => {
        e.preventDefault();
        const updatedFields = {};

        if (nimi !== tapahtuma.nimi) updatedFields.nimi = nimi;
        if (kuvaus !== tapahtuma.kuvaus) updatedFields.kuvaus = kuvaus;
        if (kategoria !== tapahtuma.kategoria) updatedFields.kategoria = kategoria;
        if (aloituspvm !== tapahtuma.aloituspvm) updatedFields.aloituspvm = aloituspvm;
        if (lopetuspvm !== tapahtuma.lopetuspvm) updatedFields.lopetuspvm = lopetuspvm;
        if (lippujaJaljella !== tapahtuma.lippujaJaljella) updatedFields.lippujaJaljella = lippujaJaljella;

        onEdit(tapahtuma.tapahtumaId, updatedFields);
        handleClose();
    };
    return (
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Muokkaa tapahtumaa</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit}>
                    <FormField label="Nimi" type="text" id="nimi" name="nimi" value={nimi} onChange={(e) => setNimi(e.target.value)} required />
                    <FormField label="Kuvaus" type="text" id="kuvaus" name="kuvaus" value={kuvaus} onChange={(e) => setKuvaus(e.target.value)} required />
                    <FormField label="Kategoria" type="text" id="kategoria" name="kategoria" value={kategoria} onChange={(e) => setKategoria(e.target.value)} required />
                    <FormField label="Aloituspvm" type="datetime-local" id="aloituspvm" name="aloituspvm" value={aloituspvm} onChange={(e) => setAloituspvm(e.target.value)} required />
                    <FormField label="Lopetuspvm" type="datetime-local" id="lopetuspvm" name="lopetuspvm" value={lopetuspvm} onChange={(e) => setLopetuspvm(e.target.value)} required />
                    <FormField label="Lippuja Jäljellä" type="number" id="lippujaJaljella" name="lippujaJaljella" value={lippujaJaljella} onChange={(e) => setLippujaJaljella(e.target.value)} required />
                    <Button variant="primary" type="submit">
                        Tallenna
                    </Button>
                </Form>
            </Modal.Body>
        </Modal>
    );
}