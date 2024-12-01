import { useState, useEffect } from 'react';
import { haeMaksutavat } from '../api/maksutavat';

export default function MyyntitapahtumaModal({ show, handleClose, tapahtuma, onSell }) {
    const [maksutavat, setMaksutavat] = useState([]);
    const [liput, setLiput] = useState(tapahtuma.lipputyypit.map(lipputyyppi => ({
        lipputyyppiId: lipputyyppi.id,
        maara: 0
    })));
    const [myyntitapahtuma, setMyyntitapahtuma] = useState({
        maksutapaId: null,
        kayttajaId: 1,
        summa: 0,
        tapahtumaId: tapahtuma.tapahtumaId,
        liput: []
    });

    useEffect(() => {
        const fetchMaksutavat = async () => {
            try {
                const response = await haeMaksutavat();
                setMaksutavat(response);
                setMyyntitapahtuma(prevState => ({ ...prevState, maksutapaId: response[0].maksutapaId }));
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
            const lipputyyppi = tapahtuma.lipputyypit.find(lt =>
                lt.id.tapahtumaId === lippu.lipputyyppiId.tapahtumaId &&
                lt.id.lipputyyppiId === lippu.lipputyyppiId.lipputyyppiId
            );
            if (lipputyyppi) {
                summa += lipputyyppi.hinta * lippu.maara;
            }
        });
        setMyyntitapahtuma(prevState => ({ ...prevState, summa }));
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
            const existingLippuIndex = updatedLiput.findIndex(lippu =>
                lippu.lipputyyppiId.tapahtumaId === newLippu.lipputyyppiId.tapahtumaId &&
                lippu.lipputyyppiId.lipputyyppiId === newLippu.lipputyyppiId.lipputyyppiId
            );

            if (existingLippuIndex !== -1) {
                updatedLiput[existingLippuIndex].maara += newLippu.maara;
            } else {
                updatedLiput.push(newLippu);
            }
        });

        setMyyntitapahtuma(prevState => ({
            ...prevState,
            liput: updatedLiput
        }));

        setLiput(tapahtuma.lipputyypit.map(lipputyyppi => ({
            lipputyyppiId: lipputyyppi.id,
            maara: 0
        })));
    };

    const handleRemoveFromCart = (index) => {
        const updatedLiput = myyntitapahtuma.liput.filter((_, i) => i !== index);
        setMyyntitapahtuma(prevState => ({
            ...prevState,
            liput: updatedLiput
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const myyntitapahtumaData = {
            ...myyntitapahtuma,
            liput: myyntitapahtuma.liput.filter(lippu => lippu.maara > 0)
        };
        try {
            await onSell(myyntitapahtumaData);
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
                            <h5 className="modal-title" id="myyntitapahtumaModalLabel">Myy Lippuja {tapahtuma.nimi} tapahtumaan</h5>
                            <button type="button" className="btn-close" aria-label="Close" onClick={handleClose}></button>
                        </div>
                        <div className="modal-body">
                            <form onSubmit={handleSubmit}>
                                {liput.map((lippu, index) => (
                                    <div key={index} className="mb-3">
                                        <label className="form-label">{tapahtuma.lipputyypit.find(lt => lt.id.lipputyyppiId === lippu.lipputyyppiId.lipputyyppiId).nimi} - {tapahtuma.lipputyypit.find(lt => lt.id.lipputyyppiId === lippu.lipputyyppiId.lipputyyppiId).hinta} €</label>
                                        <div className="input-group">
                                            <button type="button" className="btn btn-outline-secondary" onClick={() => handleMaaraChange(index, -1)}>-</button>
                                            <input type="number" className="form-control" value={lippu.maara} readOnly />
                                            <button type="button" className="btn btn-outline-secondary" onClick={() => handleMaaraChange(index, 1)}>+</button>
                                        </div>
                                    </div>
                                ))}
                                <button type="button" className="btn btn-secondary mb-3" onClick={handleAddToCart}>Lisää ostoskoriin</button>
                                <hr />
                                <h5>Ostoskorissa olevat liput</h5>
                                {myyntitapahtuma.liput.length > 0 ? (
                                    <ul className="list-group mb-3">
                                        {myyntitapahtuma.liput.map((lippu, index) => (
                                            <li key={index} className="list-group-item d-flex justify-content-between align-items-center">
                                                {tapahtuma.lipputyypit.find(lt => lt.id.lipputyyppiId === lippu.lipputyyppiId.lipputyyppiId).nimi} - {lippu.maara} kpl
                                                <span>{tapahtuma.lipputyypit.find(lt => lt.id.lipputyyppiId === lippu.lipputyyppiId.lipputyyppiId).hinta * lippu.maara} €</span>
                                                <button type="button" className="btn btn-danger btn-sm" onClick={() => handleRemoveFromCart(index)}>Poista</button>
                                            </li>
                                        ))}
                                    </ul>
                                ) : (
                                    <p>Ostoskorissa ei ole lippuja.</p>
                                )}
                                <div className="mb-3">
                                    <label htmlFor="maksutapa" className="form-label">Maksutapa</label>
                                    <select className="form-control" id="maksutapa" value={myyntitapahtuma.maksutapaId} onChange={(e) => setMyyntitapahtuma({ ...myyntitapahtuma, maksutapaId: e.target.value })} required>
                                        {maksutavat.map((maksutapa, idx) => (
                                            <option key={idx} value={maksutapa.maksutapaId}>{maksutapa.maksutapaNimi}</option>
                                        ))}
                                    </select>
                                </div>
                                <p>Summa {myyntitapahtuma.summa} €</p>
                                <button type="submit" className="btn btn-primary">Vahvista valinnat</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}