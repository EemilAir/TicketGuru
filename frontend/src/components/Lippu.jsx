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

    const printLippu = (lippu) => {
        const printContent = `
        <html>
        <head>
            <title>Tulosta Lippu</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            h1, h2 {
                color: #333;
            }
            .lippu {
                border: 1px solid #ddd;
                padding: 10px;
                margin-bottom: 10px;
                border-radius: 5px;
                background-color: #fff;
                width: 300px; /* Aseta leveys kapeammaksi */
            }
            .lippu img {
                display: block;
                margin-top: 10px;
                width: 150px; /* Suurenna QR-koodin leveys */
            }
        </style>
        </head>
        <body>
            <div class="lippu">
                <h2>${lippu.lipputyyppi}</h2>
                <p><strong>Lipun koodi:</strong> ${lippu.koodi}</p>
                <p><strong>Tapahtuma ID:</strong> ${lippu.tapahtumaId}</p>
                <p><strong>Tila:</strong> ${lippu.tila === 0 ? 'Käytetty' : 'Käyttämätön'}</p>
                <p><strong>Käyttöaika:</strong> ${lippu.kayttoaika ? formatDate(lippu.kayttoaika) : '-'}</p>
                <img src="${qrCodeUrl}" alt="QR Code" />
            </div>
        </body>
        </html>
        `;

        const printWindow = window.open('', '', 'height=600,width=800');
        printWindow.document.write(printContent);
        printWindow.document.close();
        printWindow.print();
    };

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
                    <Button variant="secondary" className="w-100 mt-2" onClick={() => printLippu(lippu)}>
                            Tulosta lippu
                    </Button>
                </div>
            </Card.Body>
        </Card>
    );
}