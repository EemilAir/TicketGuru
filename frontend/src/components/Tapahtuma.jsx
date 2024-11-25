import { useState } from 'react';
import EditTapahtumaModal from './EditTapahtumaModal';
import { formatDate } from '../utils/formatDate';

export default function Tapahtuma({ tapahtuma, onDelete, onEdit, onSellTickets }) {
    const [showModal, setShowModal] = useState(false);

    const handleEditClick = () => {
        setShowModal(true);
    };

    const handleCloseModal = () => {
        setShowModal(false);
    };

    const handleUpdate = (id, updatedTapahtuma) => {
        onEdit(id, updatedTapahtuma);
    };

    return (
        <>
            <div className="card h-100 mb-3">
                <div className="card-body d-flex flex-column">
                    <h5 className="card-title">{tapahtuma.nimi}</h5>
                    <p className="card-text">{tapahtuma.kuvaus}</p>
                    <p className="card-text"><small className="text-muted">{tapahtuma.kategoria}</small></p>
                    <p className="card-text"><small className="text-muted">Aloituspvm: {formatDate(tapahtuma.aloituspvm)}</small></p>
                    <p className="card-text"><small className="text-muted">Lopetuspvm: {formatDate(tapahtuma.lopetuspvm)}</small></p>
                    <p className="card-text"><small className="text-muted">{tapahtuma.katuosoite}, {tapahtuma.osoite.postinumero} {tapahtuma.osoite.postitmp}</small></p>
                    <div className="mt-auto">
                        <button className="btn btn-success w-100 mb-2" onClick={() => onSellTickets(tapahtuma.tapahtumaId)}>Myy lippuja</button>
                        <button className="btn btn-warning w-100 mb-2" onClick={handleEditClick}>Muokkaa</button>
                        <button className="btn btn-danger w-100" onClick={() => onDelete(tapahtuma.tapahtumaId)}>Poista</button>
                    </div>
                </div>
            </div>
            <EditTapahtumaModal
                show={showModal}
                handleClose={handleCloseModal}
                tapahtuma={tapahtuma}
                onEdit={handleUpdate}
            />
        </>
    );
}