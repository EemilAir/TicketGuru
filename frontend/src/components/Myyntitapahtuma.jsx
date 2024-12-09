import { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { Card, Spinner, Button } from 'react-bootstrap';
import Liput from './Liput';
import { fetchMyyntitapahtuma } from '../api/myyntitapahtumat';
import { formatDate } from '../utils/formatDate';
import { updateLipunTila } from '../api/liput';
import { generateQRCode } from '../utils/qrcode';

export default function Myyntitapahtuma() {
    const { id } = useParams();
    const [myyntitapahtuma, setMyyntitapahtuma] = useState({});
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const getMyyntitapahtuma = async () => {
            try {
                const response = await fetchMyyntitapahtuma(id);
                setMyyntitapahtuma(response);
            } catch (error) {
                console.error("Error fetching myyntitapahtuma:", error);
            } finally {
                setIsLoading(false);
            }
        };
        getMyyntitapahtuma();
    }, [id]);

    const handleLipunTila = async (koodi, tila) => {
        try {
            const response = await updateLipunTila(koodi, tila);
            setMyyntitapahtuma((prev) => ({
                ...prev,
                liput: prev.liput.map(lippu => lippu.koodi === koodi ? response : lippu)
            }));
        } catch (error) {
            console.error("Failed to update ticket:", error);
        }
    };

    const printMyyntitapahtuma = (myyntitapahtuma) => {
        const printContent = `
        <html>
        <head>
            <title>Tulosta myyntitapahtuma</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            h1, h2 {
                color: #333;
            }
            .myyntitapahtuma {
                border: 1px solid #ddd;
                padding: 20px;
                border-radius: 5px;
                background-color: #f9f9f9;
            }
            .lippu {
                border: 1px solid #ddd;
                padding: 10px;
                margin-bottom: 10px;
                border-radius: 5px;
                background-color: #fff;
                width: 200px;
                height: 300px;
            }
            .lippu img {
                display: block;
                margin-top: 10px;
                width: 150px; 
            }
        </style>
        </head>
        <body>
            <div class="myyntitapahtuma">
                <h1>Myyntitapahtuma: ${myyntitapahtuma.myyntitapahtumaId}</h1>
                <p><strong>Summa:</strong> ${myyntitapahtuma.summa} €</p>
                <p><strong>Maksutapa:</strong> ${myyntitapahtuma.maksutapa}</p>
                <p><strong>Maksupäivämäärä:</strong> ${formatDate(myyntitapahtuma.maksupvm)}</p>
                <p><strong>Käyttäjä ID:</strong> ${myyntitapahtuma.kayttajaId}</p>
                <p><strong>Tapahtuma ID:</strong> ${myyntitapahtuma.tapahtumaId}</p>
                <div>
                    <h2>Liput</h2>
                    ${myyntitapahtuma.liput.map(lippu => `
                        <div class="lippu">
                            <h3>${lippu.lipputyyppi}</h3>
                            <p><strong>Lippu ID:</strong> ${lippu.koodi}</p>
                            <p><strong>Tapahtuma ID:</strong> ${lippu.tapahtumaId}</p>
                            <img src="${generateQRCode(lippu)}" alt="QR Code" />
                        </div>
                    `).join('')}
                </div>
            </div>
        </body>
        </html>
        `;

        const printWindow = window.open('', '', 'height=600,width=800');
        printWindow.document.write('<html><head><title>Tulosta myyntitapahtuma</title>');
        printWindow.document.write('<link rel="stylesheet" type="text/css" href="printStyles.css" />');
        printWindow.document.write('</head><body>');
        printWindow.document.write(printContent);
        printWindow.document.write('</body></html>');
        printWindow.document.close();
        printWindow.print();
    };

    return (
        <>
            {isLoading && <Spinner animation="border" />}
            {!isLoading && myyntitapahtuma &&
                <Card>
                    <Card.Header>
                        <h1>Myyntitapahtuma: {myyntitapahtuma.myyntitapahtumaId}</h1>
                    </Card.Header>
                    <Card.Body>
                        <p><strong>Summa:</strong> {myyntitapahtuma.summa} €</p>
                        <p><strong>Maksutapa:</strong> {myyntitapahtuma.maksutapa}</p>
                        <p><strong>Maksupäivämäärä:</strong> {formatDate(myyntitapahtuma.maksupvm)}</p>
                        <p><strong>Käyttäjä ID:</strong> {myyntitapahtuma.kayttajaId}</p>
                        <p><strong>Tapahtuma ID:</strong> {myyntitapahtuma.tapahtumaId}</p>
                        <Liput liput={myyntitapahtuma.liput} handleLipunTila={handleLipunTila} />
                        <Button variant="secondary" className="mt-2" onClick={() => printMyyntitapahtuma(myyntitapahtuma)}>
                            Tulosta myyntitapahtuma
                        </Button>
                    </Card.Body>
                </Card>
            }
        </>
    );
}