import { Card, Button } from 'react-bootstrap';
import { formatDate } from '../utils/formatDate';
import { QRCodeSVG } from 'qrcode.react';
import { getLippuPrintHtml } from '../utils/getLippuPrintHtml';

export default function Lippu({ lippu, handleLipunTila }) {
    const printLippu = (lippu) => {
        const printContent = getLippuPrintHtml(lippu);

        const printWindow = window.open('', '', 'height=600,width=800');
        printWindow.document.write(printContent);
        printWindow.document.close();
        printWindow.focus();
        printWindow.print();
        printWindow.close();
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
                <QRCodeSVG value={`http://localhost:8080/api/liput?koodi=${lippu.koodi}`} />
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