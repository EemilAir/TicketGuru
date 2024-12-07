import { formatDate } from '../utils/formatDate';

export default function Tapahtuma({ tapahtuma, openSellModal, openEditModal, openDeleteModal }) {
    return (
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
                    <button className="btn btn-success w-100 mb-2" onClick={openSellModal}>Myy lippuja</button>
                    <button className="btn btn-warning w-100 mb-2" onClick={openEditModal}>Muokkaa</button>
                    <button className="btn btn-danger w-100" onClick={openDeleteModal}>Poista</button>
                </div>
            </div>
        </div>
    );
}