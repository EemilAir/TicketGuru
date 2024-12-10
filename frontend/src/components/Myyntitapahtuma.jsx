import { useEffect, useState } from 'react';
import { useParams, useNavigate, useLocation } from 'react-router';
import { Card, Spinner, Button, Badge } from 'react-bootstrap';
import Liput from './Liput';
import { fetchMyyntitapahtuma } from '../api/myyntitapahtumat';
import { formatDate } from '../utils/formatDate';
import { updateLipunTila } from '../api/liput';
import QRCode from 'qrcode';
import {
    FaUserTag,
    FaTicketAlt,
    FaMoneyBillWave,
    FaCalendarAlt
} from 'react-icons/fa';
import { getMyyntitapahtumaPrintHtml } from '../utils/getMyyntitapahtumaPrintHtml';

export default function Myyntitapahtuma({ myyntitapahtuma }) {
    const { id } = useParams();
    const location = useLocation();
    const navigate = useNavigate();

    const [myyntitapahtumaData, setMyyntitapahtumaData] = useState(myyntitapahtuma || {});
    const [isLoading, setIsLoading] = useState(!myyntitapahtuma);
    const [qrCodes, setQrCodes] = useState({});
    const [printHtml, setPrintHtml] = useState('');
    const [isPrintReady, setIsPrintReady] = useState(false);

    const isDetailView = location.pathname.includes(`/myyntitapahtumat/${id}`);

    // Fetch myyntitapahtuma if not provided as a prop
    useEffect(() => {
        const fetchMyyntitapahtumaData = async () => {
            if (!myyntitapahtuma) {
                try {
                    const response = await fetchMyyntitapahtuma(id);
                    setMyyntitapahtumaData(response);
                } catch (error) {
                    console.error('Error fetching myyntitapahtuma:', error);
                } finally {
                    setIsLoading(false);
                }
            }
        };

        fetchMyyntitapahtumaData();
    }, [id, myyntitapahtuma]);

    // Prepare QR codes and print HTML after data is ready
    useEffect(() => {
        const preparePrintHtml = async () => {
            if (!myyntitapahtumaData?.liput) return;

            const qrCodes = {};
            for (const lippu of myyntitapahtumaData.liput) {
                qrCodes[lippu.koodi] = await QRCode.toDataURL(lippu.koodi);
            }

            const liputWithQrCodes = myyntitapahtumaData.liput.map(lippu => ({
                ...lippu,
                qrCodeUrl: qrCodes[lippu.koodi],
            }));

            const htmlContent = getMyyntitapahtumaPrintHtml({
                ...myyntitapahtumaData,
                liput: liputWithQrCodes,
            });

            setQrCodes(qrCodes);
            setPrintHtml(htmlContent);
            setIsPrintReady(true);
        };

        if (myyntitapahtumaData) {
            preparePrintHtml();
        }
    }, [myyntitapahtumaData]);

    const handleLipunTila = async (koodi, tila) => {
        try {
            await updateLipunTila(koodi, tila);
            setMyyntitapahtumaData(prev => ({
                ...prev,
                liput: prev.liput.map(lippu => lippu.koodi === koodi ? { ...lippu, tila } : lippu)
            }));
        } catch (error) {
            console.error("Failed to update ticket:", error);
        }
    };

    const printMyyntitapahtuma = () => {
        if (!isPrintReady) return;

        const printWindow = window.open('', '', 'height=600,width=800');
        printWindow.document.write(printHtml);
        printWindow.document.close();
        printWindow.focus();
        printWindow.print();
        printWindow.close();
    };

    if (isLoading) {
        return <Spinner animation="border" />;
    }

    return (
        <Card className="h-100 shadow-sm border-0" style={{ border: '1px solid #ccc' }}>
            <Card.Body className="d-flex flex-column">
                <Card.Title className="d-flex justify-content-between align-items-center mb-3" style={{ backgroundColor: '#f8f9fa', padding: '10px', borderRadius: '5px' }}>
                    <span className="h5 fw-bold">Myyntitapahtuma: {myyntitapahtumaData.myyntitapahtumaId}</span>
                    <Badge bg="primary" pill>{myyntitapahtumaData.summa} €</Badge>
                </Card.Title>
                <Card.Text className="mb-2">
                    <FaMoneyBillWave className="me-2 text-success" />
                    <strong>Maksutapa:</strong> {myyntitapahtumaData.maksutapa}
                </Card.Text>
                <Card.Text className="mb-2">
                    <FaCalendarAlt className="me-2 text-info" />
                    <strong>Maksupäivämäärä:</strong> {formatDate(myyntitapahtumaData.maksupvm)}
                </Card.Text>
                <Card.Text className="mb-2">
                    <FaUserTag className="me-2 text-danger" />
                    <strong>Käyttäjä ID:</strong> {myyntitapahtumaData.kayttajaId}
                </Card.Text>
                <Card.Text className="mb-2">
                    <FaTicketAlt className="me-2 text-warning" />
                    <strong>Tapahtuma ID:</strong> {myyntitapahtumaData.tapahtumaId}
                </Card.Text>
                {isDetailView && <Liput liput={myyntitapahtumaData.liput} handleLipunTila={handleLipunTila} />}
                {!isDetailView && (
                    <Card.Text className="mb-3">
                        <FaTicketAlt className="me-2 text-warning" />
                        <strong>Lippujen määrä:</strong> {myyntitapahtumaData.liput.length}
                    </Card.Text>
                )}
                {!isDetailView &&
                    <Button variant="primary" className="mb-2" onClick={() => navigate(`/myyntitapahtumat/${myyntitapahtumaData.myyntitapahtumaId}`)}>
                        Näytä myyntitapahtuma
                    </Button>
                }
                <Button
                    variant="secondary"
                    onClick={printMyyntitapahtuma}
                >
                    Tulosta myyntitapahtuma
                </Button>
            </Card.Body>
        </Card>
    );
}