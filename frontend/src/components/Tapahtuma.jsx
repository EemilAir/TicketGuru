import { useState } from 'react';
import EditTapahtumaModal from './EditTapahtumaModal';
import MyyntitapahtumaModal from './MyyntitapahtumaModal';
import { formatDate } from '../utils/formatDate';

export default function Tapahtuma({ tapahtuma, onDelete, onEdit, onSell }) {
    const [showEditModal, setShowEditModal] = useState(false);
    const [showSellModal, setShowSellModal] = useState(false);

    const handleEditClick = () => {
        setShowEditModal(true);
    };

    const handleCloseEditModal = () => {
        setShowEditModal(false);
    };

    const handleSellClick = () => {
        setShowSellModal(true);
    };

    const handleCloseSellModal = () => {
        setShowSellModal(false);
    };

    const handleUpdate = (id, updatedTapahtuma) => {
        onEdit(id, updatedTapahtuma);
    };

    const handleSell = async (myyntitapahtuma) => {
        await onSell(myyntitapahtuma);
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
                    <p className="card-text"><small className="text-muted">Lippuja jäljellä: {tapahtuma.lippujaJaljella}</small></p>
                    <div className="mt-auto">
                        <button className="btn btn-success w-100 mb-2" onClick={handleSellClick}>Myy lippuja</button>
                        <button className="btn btn-warning w-100 mb-2" onClick={handleEditClick}>Muokkaa</button>
                        <button className="btn btn-danger w-100" onClick={() => onDelete(tapahtuma.tapahtumaId)}>Poista</button>
                    </div>
                </div>
            </div>
            <EditTapahtumaModal
                show={showEditModal}
                handleClose={handleCloseEditModal}
                tapahtuma={tapahtuma}
                onEdit={handleUpdate}
            />
            <MyyntitapahtumaModal
                show={showSellModal}
                handleClose={handleCloseSellModal}
                tapahtuma={tapahtuma}
                onSell={handleSell}
            />
        </>
    );
}