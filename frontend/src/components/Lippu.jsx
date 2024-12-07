import { useState, useEffect } from 'react';
import { Card, Button } from 'react-bootstrap';
import { formatDate } from '../utils/formatDate';
import { generateQRCode } from '../utils/qrcode';
import QRCode from 'react-qr-code';

export default function Lippu({ lippu, handleLipunTila }) {
    const [qrCodeUrl, setQrCodeUrl] = useState('');

    useEffect(() => {
        const generateQrCode = async () => {
            const url = generateQRCode(lippu);
            setQrCodeUrl(url);
        };
        generateQrCode();
    }, [lippu]);

    return (
        <Card className="mb-3">
            <Card.Header>
                <Card.Title>{lippu.lipputyyppi}</Card.Title>
                <Card.Text>Koodi: {lippu.koodi}</Card.Text>
            </Card.Header>
            <Card.Body>
                <Card.Text>
                    <strong>Tila:</strong> {lippu.tila === 0 ? 'Käytetty' : 'Käyttämätön'}
                </Card.Text>
                <Card.Text>
                    <strong>Käyttöaika:</strong> {lippu.kayttoaika ? formatDate(lippu.kayttoaika) : '-'}
                </Card.Text>
                <div className="d-flex justify-content-center align-items-center mb-3">
                    <QRCode value={qrCodeUrl} size={128} />
                </div>
                <div className="mt-3">
                    <Button variant="primary" className="w-100" onClick={() => handleLipunTila(lippu.koodi, lippu.tila === 0 ? 1 : 0)}>
                        {lippu.tila === 0 ? 'Merkitse käyttämättömäksi' : 'Merkitse käytetyksi'}
                    </Button>
                </div>
            </Card.Body>
        </Card>
    );
}