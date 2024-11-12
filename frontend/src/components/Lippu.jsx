import { useState } from 'react';

import { editLippu } from '../api/liput';

import '../css/Lippu.css';

import { generateQRCode } from '../api/qrcode';

export default function Lippu({ lippu: initialLippu, setError, setSuccess }) {

    const [expanded, setExpanded] = useState(false); // Uusi tila lisätietojen näyttämiseksi
    const [showDetails, setShowDetails] = useState(false);
    const [lippu, setLippu] = useState(initialLippu);

    const handleEditLippu = async () => {
        try {
            const updatedLippu = await editLippu(lippu); // Kutsutaan editLippu API-päivitysfunktiota
            setLippu((prevLippu) => ({
                ...prevLippu,
                kaytetty: updatedLippu.kaytetty, // Päivitetään kaytetty-arvo JSON-vastauksen perusteella
            }));
            setSuccess(`Lippu ID: ${updatedLippu.lippuId} - muokattu onnistuneesti`);
        } catch (error) {
            setError(error.message);
        }
    };

    const toggleDetails = () => {
        setExpanded(!expanded); // Vaihda tila laajennetuksi tai ei-laajennetuksi
    };

    const formatDate = (dateString) => {
        const [year, month, day] = dateString.split('-');
        return `${day}.${month}.${year}`;
    }

    const apiUrl = import.meta.env.VITE_APP_API_URL;
    const qrCodeValue = `${apiUrl}/api/liput/checkin?id=${lippu.lippuId}`;
    const qrCodeUrl = generateQRCode(qrCodeValue); // Luo QR-koodi Data URL -muodossa

    return (
        <div className={`lippu ${lippu.kaytetty ? 'used' : ''}`}>
            <div className="lippu-header">
                <div className="lippu-title">Lippu ID: {lippu.lippuId}</div>
                <button onClick={handleEditLippu}>
                    {lippu.kaytetty ? 'Käytetty' : 'Merkite käytetyksi'}
                </button>
            </div>
            <div className="lippu-details">
                <p>Tapahtuma: {lippu.tapahtuma.nimi}</p>
                <p>Paikka: {lippu.tapahtuma.paikka}</p>
                <p>Ajankohta: {formatDate(lippu.tapahtuma.aika)}</p>
                <p>Lipputyyppi: {lippu.hinnasto.hintaluokka}</p>
                <p>Hinta: {lippu.hinnasto.hinta} €</p>
            </div>
            {!lippu.kaytetty && (
                <div className="qr-code">
                    <img src={qrCodeUrl} alt="QR-koodi" width="128" height="128" />
                </div>
            )}
            {/* Näytä lisätiedot, jos expanded-tila on true */}
            {expanded && (
                <div className="lippu-extra-details">
                    <pre>{JSON.stringify(lippu, null, 2)}</pre>
                </div>
            )}
            <button onClick={toggleDetails} className="toggle-details-button">
                {expanded ? 'Piilota lisätiedot' : 'Näytä lisätiedot'}
            </button>
        </div>
    )
}