import { useEffect, useState } from 'react';
import { fetchTapahtumaFormData, fetchTapahtumat, luoTapahtuma } from '../api/tapahtumat';
import FormField from './FormField';
import { BarLoader } from 'react-spinners';
import OsoiteValinta from './OsoiteValinta';
import LipputyyppiValinta from './LipputyyppiValinta';
import { useNavigate } from 'react-router';
import { Form, Button } from 'react-bootstrap';

export default function UusiTapahtuma() {
    const navigate = useNavigate();

    const [isLoading, setIsLoading] = useState(true);
    const [tapahtumat, setTapahtumat] = useState([]);
    const [tapahtumaDTO, setTapahtumaDTO] = useState({
        nimi: '',
        kuvaus: '',
        kategoria: '',
        aloituspvm: '',
        lopetuspvm: '',
        katuosoite: '',
        lippujaJaljella: 1000,
        osoiteId: 1,
        lipputyypit: []
    });
    const [nimiExists, setNimiExists] = useState(false);

    const [osoitteet, setOsoitteet] = useState([]);
    const [lipputyypit, setLipputyypit] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetchTapahtumaFormData();
                setOsoitteet(response.osoitteet);
                setLipputyypit(response.lipputyypit);

                setTapahtumat(await fetchTapahtumat());
            } catch (error) {
                console.error("Virhe tapahtuman tietojen haussa:", error);
            } finally {
                setIsLoading(false);
            }
        };
        fetchData();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        const numericValue = name === 'lippujaJaljella' || name === 'osoiteId' ? parseInt(value) : value;
        setTapahtumaDTO(prevState => ({
            ...prevState,
            [name]: numericValue
        }));

        if (name === 'nimi') {
            const exists = tapahtumat.some(tapahtuma => tapahtuma.nimi.toLowerCase() === value.toLowerCase());
            setNimiExists(exists);
            if (exists) {
                setError(`Tapahtuma nimellä "${value}" on jo olemassa.`);
            } else {
                setError(null);
            }
        }
    };

    const handleCheckboxChange = (e) => {
        const { value, checked } = e.target;
        const numericValue = parseInt(value);
        setTapahtumaDTO(prevState => ({
            ...prevState,
            lipputyypit: checked
                ? [...prevState.lipputyypit, { id: numericValue, hinta: 0 }]
                : prevState.lipputyypit.filter(lipputyyppi => lipputyyppi.id !== numericValue)
        }));
    };

    const handleLipputyyppiChange = (e, id) => {
        const { name, value } = e.target;
        setTapahtumaDTO(prevState => ({
            ...prevState,
            lipputyypit: prevState.lipputyypit.map(lipputyyppi =>
                lipputyyppi.id === id ? { ...lipputyyppi, [name]: parseFloat(value) } : lipputyyppi
            )
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (nimiExists) return;
        try {
            await luoTapahtuma(tapahtumaDTO);
            navigate("/tapahtumat");
        } catch (error) {
            console.error("Virhe tapahtuman lisäämisessä:", error);
        }
    };

    return (
        <Form onSubmit={handleSubmit}>
            <FormField
                label="Tapahtuman nimi"
                type="text"
                id="nimi"
                name="nimi"
                value={tapahtumaDTO.nimi}
                onChange={handleChange}
                required
            />
            {error && <p className="text-danger">{error}</p>}
            <FormField label="Tapahtuman kuvaus" type="textarea" id="kuvaus" name="kuvaus" value={tapahtumaDTO.kuvaus} onChange={handleChange} required />
            <FormField label="Tapahtuman kategoria" type="text" id="kategoria" name="kategoria" value={tapahtumaDTO.kategoria} onChange={handleChange} required />
            <FormField label="Aloituspäivämäärä" type="datetime-local" id="aloituspvm" name="aloituspvm" value={tapahtumaDTO.aloituspvm} onChange={handleChange} required />
            <FormField label="Lopetuspäivämäärä" type="datetime-local" id="lopetuspvm" name="lopetuspvm" value={tapahtumaDTO.lopetuspvm} onChange={handleChange} required />
            <FormField label="Katuosoite" type="text" id="katuosoite" name="katuosoite" value={tapahtumaDTO.katuosoite} onChange={handleChange} required />
            <FormField label="Lippuja jäljellä" type="number" id="lippujaJaljella" name="lippujaJaljella" value={tapahtumaDTO.lippujaJaljella} onChange={handleChange} required />
            {isLoading &&
                <div>
                    <p>Ladataan osoitteet ja lipputyypit...</p>
                    <BarLoader color="#6610f2" className='mb-2' />
                </div>
            }
            {!isLoading &&
                <>
                    <OsoiteValinta osoitteet={osoitteet} tapahtumaDTO={tapahtumaDTO} handleChange={handleChange} />
                    <LipputyyppiValinta lipputyypit={lipputyypit} tapahtumaDTO={tapahtumaDTO} handleChange={handleCheckboxChange} handleLipputyyppiChange={handleLipputyyppiChange} />
                </>
            }
            <Button variant="primary" type="submit">Lisää tapahtuma</Button>
        </Form>
    );
}