import { useState, useEffect } from "react";

const TicketSalesForm = ({ auth }) => {
    const [formData, setFormData] = useState({
        maksutapaId: "1",
        maksupvm: "",
        kayttajaId: "1",
        liput: [{ tapahtumaId: "", lipputyyppiId: "", maara: "" }],
    });

    const [tapahtumat, setTapahtumat] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const now = new Date();
        now.setHours(now.getHours() + 2);
        const isoDate = now.toISOString();
        const formattedDate = isoDate.slice(0, -5);
        setFormData((prevData) => ({ ...prevData, maksupvm: formattedDate }));

        // Hae tapahtumat backendistä
        const fetchTapahtumat = async () => {
            try {
                const response = await fetch("http://localhost:8080/api/tapahtumat/", {
                    headers: {
                        Authorization: "Basic " + auth, // Lisätään kirjautumistiedot headeriin
                    },
                });
                if (response.ok) {
                    const data = await response.json();
                    setTapahtumat(data);
                    setLoading(false);
                } else {
                    console.error("Virhe tapahtumien haussa:", response.statusText);
                }
            } catch (error) {
                console.error("Virhe GET-pyynnössä:", error);
            }
        };

        fetchTapahtumat();
    }, [auth]);

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
                    Authorization: "Basic " + auth,
                },
                body: JSON.stringify(formData),
            });
            if (response.ok) {
                alert("Lippujen myynti onnistui!");
                resetForm();
            } else {
                alert("Virhe: " + response.statusText);
            }
        } catch (error) {
            console.error("Virhe POST-pyynnössä:", error);
            alert("POST-pyyntö epäonnistui.");
        }
    };

    const resetForm = () => {
        const now = new Date();
        now.setHours(now.getHours() + 2); 
        const isoDate = now.toISOString();
        const formattedDate = isoDate.slice(0, -5);
    
        setFormData({
            maksutapaId: "1",
            maksupvm: formattedDate,
            kayttajaId: "1",
            liput: [{ tapahtumaId: "", lipputyyppiId: "", maara: "" }],
        });
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Maksutapa ID:</label>
                <input type="number" name="maksutapaId" value={formData.maksutapaId} onChange={handleChange} required />
            </div>
            <div>
                <label>Maksupäivämäärä:</label>
                <input type="datetime-local" name="maksupvm" value={formData.maksupvm} readOnly />
            </div>
            <div>
                <label>Käyttäjä ID:</label>
                <input type="number" name="kayttajaId" value={formData.kayttajaId} onChange={handleChange} required />
            </div>
            {loading ? (
                <p>Ladataan tapahtumia...</p>
            ) : (
                formData.liput.map((lippu, index) => (
                    <div key={index}>
                        <h4>Lippu {index + 1}</h4>
                        <label>Tapahtuma:</label>
                        <select
                            name="tapahtumaId"
                            value={lippu.tapahtumaId}
                            onChange={(e) => handleLippuChange(index, e)}
                            required
                        >
                            <option value="">Valitse tapahtuma</option>
                            {tapahtumat.map((tapahtuma) => (
                                <option key={tapahtuma.tapahtumaId} value={tapahtuma.tapahtumaId}>
                                    {tapahtuma.nimi}
                                </option>
                            ))}
                        </select>
                        {lippu.tapahtumaId && (
                            <>
                                <label>Lipputyyppi:</label>
                                <select
                                    name="lipputyyppiId"
                                    value={lippu.lipputyyppiId}
                                    onChange={(e) => handleLippuChange(index, e)}
                                    required
                                >
                                    <option value="">Valitse lipputyyppi</option>
                                    {tapahtumat
                                        .find((t) => t.tapahtumaId === parseInt(lippu.tapahtumaId))
                                        ?.lipputyypit.map((lipputyyppi) => (
                                            <option
                                                key={lipputyyppi.id.lipputyyppiId}
                                                value={lipputyyppi.id.lipputyyppiId}
                                            >
                                                {lipputyyppi.nimi} - {lipputyyppi.hinta}€
                                            </option>
                                        ))}
                                </select>
                            </>
                        )}
                        <label>Määrä:</label>
                        <input
                            type="number"
                            name="maara"
                            value={lippu.maara}
                            onChange={(e) => handleLippuChange(index, e)}
                            required
                        />
                    </div>
                ))
            )}
            <button type="button" onClick={addLippu}>
                Lisää lippu
            </button>
            <button type="submit">Lähetä</button>
        </form>
    );
};

export default TicketSalesForm;
