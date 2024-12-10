import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';
import { fetchTapahtumat, editTapahtuma, deleteTapahtuma } from '../api/tapahtumat';
import { sellTickets } from '../api/myyntitapahtumat';
import Tapahtuma from './Tapahtuma';
import EditTapahtumaModal from './EditTapahtumaModal';
import MyyntitapahtumaModal from './MyyntitapahtumaModal';
import PrintAllTicketsModal from './PrintAllTicketsModal';
import DeleteTapahtumaModal from './DeleteTapahtumaModal';

export default function Tapahtumat() {
    const navigate = useNavigate();

    const [tapahtumat, setTapahtumat] = useState([]);
    const [selectedTapahtuma, setSelectedTapahtuma] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const [search, setSearch] = useState('');
    const [showEditModal, setShowEditModal] = useState(false);
    const [showSellModal, setShowSellModal] = useState(false);
    const [showSellAllModal, setShowSellAllModal] = useState(false);
    const [showDeleteModal, setShowDeleteModal] = useState(false);


    useEffect(() => {
        const getTapahtumat = async () => {
            try {
                const data = await fetchTapahtumat();
                setTapahtumat(data);
            } catch (error) {
                console.error("Fetching tapahtumat failed:", error);
                setTapahtumat([]);
            } finally {
                setIsLoading(false);
            }
        }

        getTapahtumat();
    }, []);

    const handleDelete = async (id) => {
        try {
            await deleteTapahtuma(id);
            setTapahtumat(prev => prev.filter(tapahtuma => tapahtuma.tapahtumaId !== id));
            handleCloseDeleteModal();
        } catch (error) {
            console.error("Failed to delete event:", error);
        }
    };

    const handleEdit = async (id, updatedTapahtuma) => {
        try {
            await editTapahtuma(id, updatedTapahtuma);
            setTapahtumat(tapahtumat.map(tapahtuma =>
                tapahtuma.tapahtumaId === id ? { ...tapahtuma, ...updatedTapahtuma } : tapahtuma
            ));
            setShowEditModal(false);
        } catch (error) {
            console.error("Failed to edit event:", error);
        }
    };

    const handleSell = async (myyntitapahtuma) => {
        try {
            const response = await sellTickets(myyntitapahtuma);
            const soldTicketCount = response.liput.length || 0;
            const tapahtumaId = response.tapahtumaId;
            const myyntitapahtumaId = response.myyntitapahtumaId;

            setTapahtumat(tapahtumat.map(tapahtuma =>
                tapahtuma.tapahtumaId === tapahtumaId
                    ? { ...tapahtuma, lippujaJaljella: tapahtuma.lippujaJaljella - soldTicketCount }
                    : tapahtuma
            ));

            navigate(`/myyntitapahtumat/${myyntitapahtumaId}`);
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
        setShowSellAllModal(false);
        setShowSellModal(false);
        setShowEditModal(true);
        setSelectedTapahtuma(tapahtuma);
    };

    const handleCloseEditModal = () => {
        setShowEditModal(false);
        setSelectedTapahtuma(null);
    };

    const handleShowSellModal = (tapahtuma) => {
        setShowSellAllModal(false);
        setShowEditModal(false);
        setShowSellModal(true);
        setSelectedTapahtuma(tapahtuma);
    };

    const handleCloseSellModal = () => {
        setShowSellModal(false);
        setSelectedTapahtuma(null);
    };

    const handleShowSellAllModal = (tapahtuma) => {
        setShowSellModal(false);
        setShowEditModal(false);
        setShowSellAllModal(true);
        setSelectedTapahtuma(tapahtuma);
    };

    const handleCloseSellAllModal = () => {
        setShowSellAllModal(false);
        setSelectedTapahtuma(null);
    };

    const handleShowDeleteModal = (tapahtuma) => {
        setShowSellModal(false);
        setShowEditModal(false);
        setShowSellAllModal(false);
        setShowDeleteModal(true);
        setSelectedTapahtuma(tapahtuma);
    }

    const handleCloseDeleteModal = () => {
        setShowDeleteModal(false);
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
                            openEditModal={() => handleShowEditModal(tapahtuma)}
                            openSellModal={() => handleShowSellModal(tapahtuma)}
                            openSellAllModal={() => handleShowSellAllModal(tapahtuma)}
                            openDeleteModal={() => handleShowDeleteModal(tapahtuma)}
                        />
                    </div>
                ))}
            </div>
            {showEditModal &&
                <EditTapahtumaModal
                    handleClose={handleCloseEditModal}
                    tapahtuma={selectedTapahtuma}
                    onEdit={handleEdit}
                    show={showEditModal}
                />}
            {showSellModal &&
                <MyyntitapahtumaModal
                    handleClose={handleCloseSellModal}
                    tapahtuma={selectedTapahtuma}
                    onSell={handleSell}
                    show={showSellModal}
                />
            }
            {showSellAllModal &&
                <PrintAllTicketsModal
                    handleClose={handleCloseSellAllModal}
                    tapahtuma={selectedTapahtuma}
                    onSell={handleSell}
                    show={showSellAllModal}
                />
            }
            {showDeleteModal &&
                <DeleteTapahtumaModal
                    tapahtuma={selectedTapahtuma}
                    show={showDeleteModal}
                    handleClose={handleCloseDeleteModal}
                    handleDelete={handleDelete}
                />
            }
        </div>
    );
}