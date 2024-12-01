import { useEffect, useState } from 'react';
import { fetchTapahtumat } from '../api/tapahtumat';
import { sellTickets } from '../api/maksutapahtuma';
import Tapahtuma from './Tapahtuma';

import EditTapahtumaModal from './EditTapahtumaModal';
import MyyntitapahtumaModal from './MyyntitapahtumaModal';

export default function Tapahtumat() {
    const [tapahtumat, setTapahtumat] = useState([]);
    const [selectedTapahtuma, setSelectedTapahtuma] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const [search, setSearch] = useState('');
    const [showEditModal, setShowEditModal] = useState(false);
    const [showSellModal, setShowSellModal] = useState(false);


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

    const handleSell = async (myyntitapahtuma) => {
        try {
            const response = await sellTickets(myyntitapahtuma);
            console.log("Sold tickets:", response);
        } catch (error) {
            console.error("Failed to sell tickets:", error);
        }
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

    const handleShowEditModal = (tapahtuma) => {
        setShowEditModal(true);
        setSelectedTapahtuma(tapahtuma);
    };

    const handleCloseEditModal = () => {
        setShowEditModal(false);
        setSelectedTapahtuma(null);
    };

    const handleShowSellModal = (tapahtuma) => {
        setShowSellModal(true);
        setSelectedTapahtuma(tapahtuma);
    };

    const handleCloseSellModal = () => {
        setShowSellModal(false);
        setSelectedTapahtuma(null);
    };

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
                            openEditModal={() => handleShowEditModal(tapahtuma)}
                            openSellModal={() => handleShowSellModal(tapahtuma)}
                        />
                    </div>
                ))}
            </div>
            {showEditModal &&
                <EditTapahtumaModal
                    show={showEditModal}
                    handleClose={handleCloseEditModal}
                    tapahtuma={selectedTapahtuma}
                    onEdit={handleEdit}
                />}
            {showSellModal &&
                <MyyntitapahtumaModal
                    show={showSellModal}
                    handleClose={handleCloseSellModal}
                    tapahtuma={selectedTapahtuma}
                    onSell={handleSell}
                />
            }
        </div>
    );
}