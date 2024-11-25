import { useState } from 'react';
import { editTapahtuma } from '../api/tapahtumat';

export default function EditTapahtumaModal({ show, handleClose, tapahtuma, onEdit }) {
    const [nimi, setNimi] = useState(tapahtuma.nimi);
    const [kuvaus, setKuvaus] = useState(tapahtuma.kuvaus);
    const [kategoria, setKategoria] = useState(tapahtuma.kategoria);
    const [aloituspvm, setAloituspvm] = useState(tapahtuma.aloituspvm);
    const [lopetuspvm, setLopetuspvm] = useState(tapahtuma.lopetuspvm);
    const [lippujaJaljella, setLippujaJaljella] = useState(tapahtuma.lippujaJaljella);

    // Track initial values
    const initialValues = {
        nimi: tapahtuma.nimi,
        kuvaus: tapahtuma.kuvaus,
        kategoria: tapahtuma.kategoria,
        aloituspvm: tapahtuma.aloituspvm,
        lopetuspvm: tapahtuma.lopetuspvm,
        lippujaJaljella: tapahtuma.lippujaJaljella
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const editedFields = {};

        // Compare current values with initial values and add changed fields to editedFields
        if (nimi !== initialValues.nimi) editedFields.nimi = nimi;
        if (kuvaus !== initialValues.kuvaus) editedFields.kuvaus = kuvaus;
        if (kategoria !== initialValues.kategoria) editedFields.kategoria = kategoria;
        if (aloituspvm !== initialValues.aloituspvm) editedFields.aloituspvm = aloituspvm;
        if (lopetuspvm !== initialValues.lopetuspvm) editedFields.lopetuspvm = lopetuspvm;
        if (lippujaJaljella !== initialValues.lippujaJaljella) editedFields.lippujaJaljella = lippujaJaljella;

        try {
            const response = await editTapahtuma(tapahtuma.tapahtumaId, editedFields);
            onEdit(tapahtuma.tapahtumaId, response);
            handleClose();
        } catch (error) {
            console.error("Updating tapahtuma failed:", error);
        }
    };

    return (
        <>
            {show && <div className="modal-backdrop fade show"></div>}
            <div className={`modal fade ${show ? 'show' : ''}`} style={{ display: show ? 'block' : 'none' }} tabIndex="-1" aria-labelledby="editTapahtumaModalLabel" aria-hidden={!show}>
                <div className="modal-dialog modal-dialog-scrollable">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="editTapahtumaModalLabel">Muokkaa Tapahtumaa</h5>
                            <button type="button" className="btn-close" aria-label="Close" onClick={handleClose}></button>
                        </div>
                        <div className="modal-body">
                            <form onSubmit={handleSubmit}>
                                <div className="mb-3">
                                    <label htmlFor="nimi" className="form-label">Nimi</label>
                                    <input type="text" className="form-control" id="nimi" value={nimi} onChange={(e) => setNimi(e.target.value)} />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="kuvaus" className="form-label">Kuvaus</label>
                                    <input type="text" className="form-control" id="kuvaus" value={kuvaus} onChange={(e) => setKuvaus(e.target.value)} />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="kategoria" className="form-label">Kategoria</label>
                                    <input type="text" className="form-control" id="kategoria" value={kategoria} onChange={(e) => setKategoria(e.target.value)} />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="aloituspvm" className="form-label">Aloituspvm</label>
                                    <input type="datetime-local" className="form-control" id="aloituspvm" value={aloituspvm} onChange={(e) => setAloituspvm(e.target.value)} />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="lopetuspvm" className="form-label">Lopetuspvm</label>
                                    <input type="datetime-local" className="form-control" id="lopetuspvm" value={lopetuspvm} onChange={(e) => setLopetuspvm(e.target.value)} />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="lippujaJaljella" className="form-label">Lippuja Jäljella</label>
                                    <input type="number" className="form-control" id="lippujaJaljella" value={lippujaJaljella} onChange={(e) => setLippujaJaljella(e.target.value)} />
                                </div>
                                <button type="submit" className="btn btn-primary">Tallenna</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};