import { Form } from 'react-bootstrap';

export default function FormField({ label, type, id, name, value, onChange, options, optionKey, optionValue, optionLabel, required = false }) {
    if (type === 'select') {
        return (
            <Form.Group className="mb-3">
                <Form.Label htmlFor={id}>{label}</Form.Label>
                <Form.Select
                    id={id}
                    name={name}
                    value={value}
                    onChange={onChange}
                    required={required}
                >
                    <option value="" disabled>Valitse {label}</option>
                    {options.map(option => (
                        <option key={optionKey(option)} value={optionValue(option)}>
                            {optionLabel(option)}
                        </option>
                    ))}
                </Form.Select>
            </Form.Group>
        );
    }

    if (type === 'textarea') {
        return (
            <Form.Group className="mb-3">
                <Form.Label htmlFor={id}>{label}</Form.Label>
                <Form.Control
                    as="textarea"
                    id={id}
                    name={name}
                    value={value}
                    onChange={onChange}
                    rows="3"
                    required={required}
                />
            </Form.Group>
        );
    }

    if (type === 'checkbox') {
        return (
            <Form.Group className="mb-3">
                <Form.Check
                    type="checkbox"
                    id={id}
                    name={name}
                    value={value}
                    onChange={onChange}
                    label={label}
                    required={required}
                />
            </Form.Group>
        );
    }

    return (
        <Form.Group className="mb-3">
            <Form.Label htmlFor={id}>{label}</Form.Label>
            <Form.Control
                type={type}
                id={id}
                name={name}
                value={value}
                onChange={onChange}
                required={required}
            />
        </Form.Group>
    );
}