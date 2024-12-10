import { Card, Button } from 'react-bootstrap';
import { formatDate } from '../utils/formatDate';

export default function Tapahtuma({ tapahtuma, openSellModal, openEditModal, openSellAllModal, openDeleteModal }) {
    return (
        <Card className="h-100 mb-3">
            <Card.Body className="d-flex flex-column">
                <Card.Title>{tapahtuma.nimi}</Card.Title>
                <Card.Text>{tapahtuma.kuvaus}</Card.Text>
                <Card.Text><small className="text-muted">{tapahtuma.kategoria}</small></Card.Text>
                <Card.Text><small className="text-muted">Aloituspvm: {formatDate(tapahtuma.aloituspvm)}</small></Card.Text>
                <Card.Text><small className="text-muted">Lopetuspvm: {formatDate(tapahtuma.lopetuspvm)}</small></Card.Text>
                <Card.Text><small className="text-muted">{tapahtuma.katuosoite}, {tapahtuma.osoite.postinumero} {tapahtuma.osoite.postitmp}</small></Card.Text>
                <Card.Text><small className="text-muted">Lippuja jäljellä: {tapahtuma.lippujaJaljella}</small></Card.Text>
                <div className="mt-auto">
                    <Button variant="success" className="w-100 mb-2" onClick={openSellModal}>Myy lippuja</Button>
                    <Button variant="warning" className="w-100 mb-2" onClick={openEditModal}>Muokkaa</Button>
                    <Button variant="primary" className="w-100 mb-2" onClick={openSellAllModal}>Tulosta kaikki</Button>
                    <Button variant="danger" className="w-100" onClick={openDeleteModal}>Poista</Button>
                </div>
            </Card.Body>
        </Card>
    );
}