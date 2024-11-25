import { useEffect, useState } from 'react';
import { fetchTapahtumat } from '../api/tapahtumat';
import Tapahtuma from './Tapahtuma';

export default function Tapahtumat() {

    const [tapahtumat, setTapahtumat] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {

        const getTapahtumat = async () => {
            try {
                const data = await fetchTapahtumat();
                setTapahtumat(data);
                setIsLoading(false);
            } catch (error) {
                console.error("Fetching tapahtumat failed:", error);
            }
        }

        getTapahtumat();
    }, []);

    const handleDelete = (id) => {
        console.log(`Delete event with id: ${id}`);
        // Implement delete functionality here
    };

    const handleEdit = (id) => {
        console.log(`Edit event with id: ${id}`);
        // Implement edit functionality here
    };

    const handleSellTickets = (id) => {
        console.log(`Sell tickets for event with id: ${id}`);
        // Implement sell tickets functionality here
    };

    if (isLoading) {
        return <p>Loading...</p>
    }

    if (tapahtumat.length === 0) {
        return <p>No events found</p>
    }

    return (
        <div className="container mt-4">
            <div className="row">
                {tapahtumat.map(tapahtuma => (
                    <div className="col-lg-3 col-md-4 col-sm-6 mb-4" key={tapahtuma.tapahtumaId}>
                        <Tapahtuma
                            tapahtuma={tapahtuma}
                            onDelete={handleDelete}
                            onEdit={handleEdit}
                            onSellTickets={handleSellTickets}
                        />
                    </div>
                ))}
            </div>
        </div>
    );
}

