import FormField from "./FormField"

export default function OsoiteSelect({ osoitteet, tapahtumaDTO, handleChange }) {
    return (
        <FormField
            label="Osoite"
            type="select"
            id="osoiteId"
            name="osoiteId"
            value={tapahtumaDTO.osoiteId}
            onChange={handleChange}
            options={osoitteet}
            optionKey={option => option.osoiteId}
            optionValue={option => option.osoiteId}
            optionLabel={option => `${option.postinumero} ${option.postitmp}`}
        />
    )
}