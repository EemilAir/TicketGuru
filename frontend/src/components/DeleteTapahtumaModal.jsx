import { Button, Modal } from 'react-bootstrap';

export default function DeleteTapahtumaModal({ tapahtuma, show, handleClose, handleDelete }) {
    return (
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Poista tapahtuma</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <p>Oletko varma, että haluat poistaa tapahtuman {tapahtuma.nimi}?</p>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Peruuta
                </Button>
                <Button variant="danger" onClick={() => handleDelete(tapahtuma.tapahtumaId)}>
                    Poista
                </Button>
            </Modal.Footer>
        </Modal>
    );
}