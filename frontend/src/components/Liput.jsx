import { Row, Col } from 'react-bootstrap';
import Lippu from './Lippu';

export default function Liput({ liput, handleLipunTila }) {
    return (
        <Row>
            {liput.map((lippu) => (
                <Col key={lippu.koodi} xs={12} md={6} lg={4} className="mb-4">
                    <Lippu lippu={lippu} handleLipunTila={handleLipunTila} />
                </Col>
            ))}
        </Row>
    );
}