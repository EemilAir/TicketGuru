import { useState, useEffect } from 'react';

export default function MyyntitapahtumaModal({ show, handleClose, tapahtuma, onSell }) {
    const [liput, setLiput] = useState([{ lipputyyppi: '', maara: 1 }]);
    const [maksutapa, setMaksutapa] = useState('');
    const [maksupaiva, setMaksupaiva] = useState(new Date().toISOString().split('T')[0]);
    const [kayttajaId, setKayttajaId] = useState('');
    const [summa, setSumma] = useState(0);

    const handleLippuChange = (index, field, value) => {
        const newLiput = [...liput];
        newLiput[index][field] = value;
        setLiput(newLiput);
    };

    const addLippu = () => {
        setLiput([...liput, { lipputyyppi: '', maara: 1 }]);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const myyntitapahtuma = {
            liput,
            maksutapa,
            maksupaiva,
            kayttajaId,
            summa,
            tapahtumaId: tapahtuma.tapahtumaId
        };

        try {
            await onSell(myyntitapahtuma);
            handleClose();
        } catch (error) {
            console.error("Selling tickets failed:", error);
        }
    };
    

    return (
        <>
            {show && <div className="modal-backdrop fade show"></div>}
            <div className={`modal fade ${show ? 'show' : ''}`} style={{ display: show ? 'block' : 'none' }} tabIndex="-1" aria-labelledby="myyntitapahtumaModalLabel" aria-hidden={!show}>
                <div className="modal-dialog modal-dialog-scrollable">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="myyntitapahtumaModalLabel">Myy Lippuja</h5>
                            <button type="button" className="btn-close" aria-label="Close" onClick={handleClose}></button>
                        </div>
                        <div className="modal-body">
                            <form onSubmit={handleSubmit}>
                                <div className="mb-3">
                                    <label htmlFor="tapahtumaNimi" className="form-label">Tapahtuman Nimi</label>
                                    <p className="form-control-plaintext" id="summa">{tapahtuma.nimi}</p>
                                </div>
                                {liput.map((lippu, index) => (
                                    <div key={index} className="mb-3">
                                        <label htmlFor={`lipputyyppi-${index}`} className="form-label">Lipputyyppi</label>
                                        <select className="form-control" id={`lipputyyppi-${index}`} value={lippu.lipputyyppi} onChange={(e) => handleLippuChange(index, 'lipputyyppi', e.target.value)} required>
                                            <option value="">Valitse lipputyyppi</option>
                                            {tapahtuma.lipputyypit.map((lipputyyppi, idx) => (
                                                <option key={idx} value={lipputyyppi.lipputyyppiId}>
                                                    {lipputyyppi.nimi} - {lipputyyppi.hinta} € / kpl
                                                </option>
                                            ))}
                                        </select>
                                        <label htmlFor={`maara-${index}`} className="form-label">Määrä</label>
                                        <input type="number" className="form-control" id={`maara-${index}`} value={lippu.maara} onChange={(e) => handleLippuChange(index, 'maara', e.target.value)} required />
                                    </div>
                                ))}
                                <button type="button" className="btn btn-secondary mb-3" onClick={addLippu}>Lisää toinen lippu</button>
                                <div className="mb-3">
                                    <label htmlFor="maksutapa" className="form-label">Maksutapa</label>
                                    <select className="form-control" id="maksutapa" value={maksutapa} onChange={(e) => setMaksutapa(e.target.value)} required>
                                        <option value="">Valitse maksutapa</option>
                                        <option value="Käteinen">Käteinen</option>
                                        <option value="Debit">Debit</option>
                                        <option value="Credit">Credit</option>
                                        <option value="MobilePay">MobilePay</option>
                                        <option value="PayPal">PayPal</option>
                                    </select>
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="maksupaiva" className="form-label">Maksupäivämäärä</label>
                                    <input type="date" className="form-control" id="maksupaiva" value={maksupaiva} onChange={(e) => setMaksupaiva(e.target.value)} readOnly />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="kayttajaId" className="form-label">Käyttäjä ID</label>
                                    <input type="number" className="form-control" id="kayttajaId" value={kayttajaId} onChange={(e) => setKayttajaId(e.target.value)} required />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="summa" className="form-label">Kokonaissumma</label>
                                    <p className="form-control-plain-number" id="summa" value={summa} onChange={(e) => setSumma(e.target.value)}>{summa} €</p> 
                                </div>
                                <button type="submit" className="btn btn-primary">Vahvista valinnat</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}