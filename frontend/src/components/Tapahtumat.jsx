import { useEffect, useState } from 'react';
import { fetchTapahtumat } from '../api/tapahtumat';
import Tapahtuma from './Tapahtuma';

export default function Tapahtumat() {
    const [tapahtumat, setTapahtumat] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [search, setSearch] = useState('');

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

    const handleEdit = (id, updatedTapahtuma) => {
        setTapahtumat(tapahtumat.map(tapahtuma =>
            tapahtuma.tapahtumaId === id ? { ...tapahtuma, ...updatedTapahtuma } : tapahtuma
        ));
    };

    const handleSellTickets = (id) => {
        console.log(`Sell tickets for event with id: ${id}`);
        // Implement sell tickets functionality here
    };

    const handleSearch = (event) => {
        setSearch(event.target.value);
    };

    const filteredTapahtumat = tapahtumat.filter(tapahtuma =>
        tapahtuma.nimi.toLowerCase().includes(search.toLowerCase())
    );

    if (isLoading) {
        return <p>Loading...</p>
    }

    if (tapahtumat.length === 0) {
        return <p>No events found</p>
    }

    return (
        <div className="container mt-4">
            <div className="row mb-3">
                <div className="col">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Etsi nimellä"
                        value={search}
                        onChange={handleSearch}
                    />
                </div>
            </div>
            <div className="row">
                {filteredTapahtumat.map(tapahtuma => (
                    <div className="col-lg-3 col-md-4 col-sm-6 mb-4 d-flex" key={tapahtuma.tapahtumaId}>
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