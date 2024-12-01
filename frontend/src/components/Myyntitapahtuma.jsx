import { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { fetchMyyntitapahtuma } from '../api/myyntitapahtumat';
import Liput from './Liput';

function Myyntitapahtuma() {
    const { id } = useParams();
    const [myyntitapahtuma, setMyyntitapahtuma] = useState(null);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const getMyyntitapahtuma = async () => {
            try {
                const data = await fetchMyyntitapahtuma(id);
                setMyyntitapahtuma(data);
                setIsLoading(false);
            } catch (error) {
                console.error("Fetching myyntitapahtuma failed:", error);
                setIsLoading(false);
            }
        }
        getMyyntitapahtuma();
    }, [id]);

    const formatDate = (dateString) => {
        const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
        return new Date(dateString).toLocaleDateString(undefined, options);
    };

    return (
        <div className="container mt-5">
            {isLoading && <div>Loading...</div>}
            {!isLoading && myyntitapahtuma &&
                <div className="card">
                    <div className="card-header">
                        <h1>Myyntitapahtuma: {myyntitapahtuma.myyntitapahtumaId}</h1>
                    </div>
                    <div className="card-body">
                        <p><strong>Summa:</strong> {myyntitapahtuma.summa} €</p>
                        <p><strong>Maksutapa:</strong> {myyntitapahtuma.maksutapa}</p>
                        <p><strong>Maksupäivämäärä:</strong> {formatDate(myyntitapahtuma.maksupvm)}</p>
                        <p><strong>Käyttäjä ID:</strong> {myyntitapahtuma.kayttajaId}</p>
                        <p><strong>Tapahtuma ID:</strong> {myyntitapahtuma.tapahtumaId ? myyntitapahtuma.tapahtumaId : 'N/A'}</p>
                        <Liput liput={myyntitapahtuma.liput} />
                    </div>
                </div>
            }
        </div>
    );
}

export default Myyntitapahtuma;