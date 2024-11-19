import React, { useState } from "react";

const TicketSalesForm = () => {
    const [formData, setFormData] = useState({
        maksutapaId: "",
        maksupvm: "",
        kayttajaId: "",
        liput: [{ tapahtumaId: "", lipputyyppiId: "", maara: "" }],
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleLippuChange = (index, e) => {
        const { name, value } = e.target;
        const newLiput = [...formData.liput];
        newLiput[index][name] = value;
        setFormData({ ...formData, liput: newLiput });
    };

    const addLippu = () => {
        setFormData({
            ...formData,
            liput: [...formData.liput, { tapahtumaId: "", lipputyyppiId: "", maara: "" }],
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch("http://localhost:8080/api/myyntitapahtumat/", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: "Basic " + btoa("user:user"),
                },
                body: JSON.stringify(formData),
            });
            if (response.ok) {
                alert("Lippujen myynti onnistui!");
            } else {
                alert("Virhe: " + response.statusText);
            }
        } catch (error) {
            console.error("Virhe POST-pyynnössä:", error);
            alert("POST-pyyntö epäonnistui.");
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Maksutapa ID:</label>
                <input type="number" name="maksutapaId" value={formData.maksutapaId} onChange={handleChange} required />
            </div>
            <div>
                <label>Maksupäivämäärä:</label>
                <input type="datetime-local" name="maksupvm" value={formData.maksupvm} onChange={handleChange} required />
            </div>
            <div>
                <label>Käyttäjä ID:</label>
                <input type="number" name="kayttajaId" value={formData.kayttajaId} onChange={handleChange} required />
            </div>
            {formData.liput.map((lippu, index) => (
                <div key={index}>
                    <h4>Lippu {index + 1}</h4>
                    <label>Tapahtuma ID:</label>
                    <input
                        type="number"
                        name="tapahtumaId"
                        value={lippu.tapahtumaId}
                        onChange={(e) => handleLippuChange(index, e)}
                        required
                    />
                    <label>Lipputyyppi ID:</label>
                    <input
                        type="number"
                        name="lipputyyppiId"
                        value={lippu.lipputyyppiId}
                        onChange={(e) => handleLippuChange(index, e)}
                        required
                    />
                    <label>Määrä:</label>
                    <input
                        type="number"
                        name="maara"
                        value={lippu.maara}
                        onChange={(e) => handleLippuChange(index, e)}
                        required
                    />
                </div>
            ))}
            <button type="button" onClick={addLippu}>
                Lisää lippu
            </button>
            <button type="submit">Lähetä</button>
        </form>
    );
};

export default TicketSalesForm;