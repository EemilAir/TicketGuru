import { useState } from 'react';
import { generateQRCode } from '../api/qrcode';
import { updateLipunTila } from '../api/liput';

import { formatDate } from '../utils/formatDate';

export default function Lippu({ lippu: initialLippu }) {
    const [lippu, setLippu] = useState(initialLippu); // Lippu-tila, jota muokataan

    const handleLipunTila = async () => {
        try {
            const updatedLippu = await updateLipunTila(lippu.koodi, { tila: lippu.tila === 1 ? 0 : 1 }); // Vaihda tila
            setLippu(updatedLippu);
        } catch (error) {
            console.error('Failed to update ticket:', error);
        }
    };

    const qrCodeUrl = generateQRCode(lippu); // luo QR-koodin URL-osoite

    return (
        <div>
            <div className="card mb-3">
                <div className="card-header">
                    <h5 className="card-title">{lippu.lipputyyppi}</h5>
                    <p className="card-text">Koodi: {lippu.koodi}</p>
                </div>
                <div className="card-body">
                    <p className="card-text">
                        <strong>Tila:</strong> {lippu.tila} ({lippu.tila === 0 ? 'Käytetty' : 'Käyttämätön'})
                    </p>
                    <p className="card-text">
                        <strong>Käyttöaika:</strong> {lippu.kayttoaika ? formatDate(lippu.kayttoaika) : 'N/A'}
                    </p>
                    <img src={qrCodeUrl} alt="QR Code" className="img-fluid" />
                    <div className="mt-3">
                        <button className="btn btn-primary w-100" onClick={handleLipunTila}>
                            {lippu.tila === 0 ? 'Merkitse käyttämättömäksi' : 'Merkitse käytetyksi'}
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}
