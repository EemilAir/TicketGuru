import { useState } from 'react';
import { fetchLippuById, fetchLiput } from '../api/liput';
import Lippu from './Lippu';

export default function Liput({ liput, setLiput, setError, setSuccess }) {

    const [lippuId, setLippuId] = useState(null);

    const handleFetchLiput = async () => {
        try {
            const liput = await fetchLiput();
            setLiput(liput);
        } catch (error) {
            setError(error.message);
        }
    }

    const handleFetchLippu = async (e) => {
        e.preventDefault();
        if (!lippuId) return;
        try {
            const lippu = await fetchLippuById(lippuId);
            setLiput([lippu]);
        } catch (error) {
            console.error("ERROR:",error);
            setError(error.message);
        }
    }

    return (
        <div>
            <h1>Liput</h1>
            <div>
                <button onClick={handleFetchLiput}>Hae kaikki liput</button>
            </div>
            <form onSubmit={handleFetchLippu}>
                <input
                    type="number"
                    id="lippu-id"
                    placeholder='lipun id'
                    min="1"
                    onChange={(event) => setLippuId(event.target.value)}
                    required />
                <button type="submit">Hae Lippu</button>
            </form>

            {liput && liput.length > 0 ?
                liput.map(lippu => <Lippu key={lippu.lippuId} lippu={lippu} setError={setError} setSuccess={setSuccess} />)
                : <p>Ei lippuja</p>
            }
        </div>
    )
}