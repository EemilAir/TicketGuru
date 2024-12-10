import { Form, Row, Col } from 'react-bootstrap';

export default function LipputyyppiValinta({ lipputyypit, tapahtumaDTO, handleChange, handleLipputyyppiChange }) {
    return (
        <Form.Group className="mb-3">
            <Form.Label>Lipputyypit</Form.Label>
            {lipputyypit.map(({ id, lipputyyppiNimi }) => (
                <div key={id} className="mb-3">
                    <Form.Check
                        type="checkbox"
                        id={`lipputyyppi-${id}`}
                        name="lipputyypit"
                        value={id}
                        label={lipputyyppiNimi}
                        checked={tapahtumaDTO.lipputyypit.some(lipputyyppi => lipputyyppi.id === id)}
                        onChange={handleChange}
                    />
                    {tapahtumaDTO.lipputyypit.some(lipputyyppi => lipputyyppi.id === id) && (
                        <Row>
                            <Col>
                                <Form.Label>Hinta</Form.Label>
                                <Form.Control
                                    type="number"
                                    placeholder="Hinta"
                                    name="hinta"
                                    value={tapahtumaDTO.lipputyypit.find(lipputyyppi => lipputyyppi.id === id).hinta}
                                    onChange={(e) => handleLipputyyppiChange(e, id)}
                                    required
                                />
                            </Col>
                        </Row>
                    )}
                </div>
            ))}
        </Form.Group>
    );
}