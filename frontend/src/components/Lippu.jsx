import { useState } from 'react';

import { editLippu } from '../api/liput';

export default function Lippu({ lippu: initialLippu, setError, setSuccess }) {

    const [showDetails, setShowDetails] = useState(false);
    const [lippu, setLippu] = useState(initialLippu);

    const handleEditLippu = async () => {
        try {
            const editedLippu = await editLippu(lippu);
            setLippu(editedLippu);
            setSuccess(`Lippu ID: ${editedLippu.lippuId} - muokattu onnistuneesti`);
        } catch (error) {
            setError(error.message);
        }
    }

    return (
        <div>
            <p>Lippu ID: {lippu.lippuId}</p>
            <p>Käytetty: {lippu.kaytetty.toString()} <button onClick={handleEditLippu}>Toggle Käytetty</button> </p>
            {showDetails ? (
                <>
                    <button onClick={() => setShowDetails(false)}>Piilota lisätiedot</button>
                    <pre>
                        {JSON.stringify(lippu, null, 2)}
                    </pre>
                </>
            ) : (
                <>
                    <div>
                        <button onClick={() => setShowDetails(true)}>Näytä Lisätiedot</button>
                    </div>
                </>
            )}
        </div>
    )
}