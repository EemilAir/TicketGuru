export default function Tapahtuma({ tapahtuma }) {
    return (
        <div className="card h-100 mb-3">
            <div className="card-body d-flex flex-column">
                <h5 className="card-title">{tapahtuma.nimi}</h5>
                <p className="card-text">{tapahtuma.kuvaus}</p>
                <p className="card-text"><small className="text-muted">{tapahtuma.kategoria}</small></p>
                <p className="card-text"><small className="text-muted">{tapahtuma.aloituspvm} - {tapahtuma.lopetuspvm}</small></p>
                <p className="card-text"><small className="text-muted">{tapahtuma.katuosoite}, {tapahtuma.osoite.postinumero} {tapahtuma.osoite.postitmp}</small></p>
                <div className="mt-auto">
                    <button className="btn btn-success w-100 mb-2" onClick={() => onSellTickets(tapahtuma.tapahtumaId)}>Myy lippuja</button>
                    <button className="btn btn-warning w-100 mb-2" onClick={() => onEdit(tapahtuma.tapahtumaId)}>Muokkaa</button>
                    <button className="btn btn-danger w-100" onClick={() => onDelete(tapahtuma.tapahtumaId)}>Poista</button>
                </div>
            </div>
        </div>
    );
}