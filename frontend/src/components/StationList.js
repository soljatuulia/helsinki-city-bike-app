import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Table, NavLink, Modal } from 'react-bootstrap';

import { fetchStationDetails, initializeStations, setSelectedStationDetails } from '../reducers/stationReducer';

const StationList = () => {
  const [currentPage, setCurrentPage] = useState(0);
  const stations = useSelector((state) => state.station.stations);
  const stationDetails = useSelector((state) => state.station.stationDetails);

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(initializeStations(currentPage));
  }, [currentPage, dispatch]);

  const handlePageForward = () => {
    setCurrentPage((page) => page + 1);
  };

  const handlePageBack = () => {
    if (currentPage > 0) {
        setCurrentPage((page) => page - 1);
    }
  };

  const handleShowDetails = (stationId) => {
    dispatch(fetchStationDetails(stationId));
  };

  const handleCloseDetails = () => {
    dispatch(setSelectedStationDetails(null));
  };

  return (
    <div style={{ width: "90%", margin: "0 auto" }}>
      <h2 style={{ textAlign: "center", margin: "20px auto auto" }}>Stations</h2>
      <Table 
        variant="default"
        style={{ width:"100%", margin: "20px auto" }}
        striped
        hover
        responsive>
        <thead>
          <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Info</th>
          </tr>
        </thead>
        <tbody>
          {stations.content.map((station) => (
            <tr key={station.stationId}>
              <td>{station.name}</td>
              <td>{station.address}</td>
              <td>
                <NavLink
                  value={station.stationId}
                  onClick={() => handleShowDetails(station.stationId)}
                  style={{ color: "DarkGoldenRod" }}
                >
                  station details
                </NavLink>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
      <Modal show={!!stationDetails} onHide={handleCloseDetails}>
        <Modal.Header closeButton>
          <Modal.Title>Station Details</Modal.Title>
       </Modal.Header>
      <Modal.Body>
        {stationDetails && (
        <div>
          <p><b>Name:</b> {stationDetails.name}</p>
          <p><b>Address:</b> {stationDetails.address}</p>
          <p><b>Number of departures:</b> {stationDetails.totalDepartures}</p>
          <p><b>Number of returns:</b> {stationDetails.totalReturns}</p>
          <p><b>Average length of journeys starting here:</b> {stationDetails.averageDepartureDistance.toFixed(2)} km</p>
          <p><b>Average length of journeys ending here:</b> {stationDetails.averageReturnDistance.toFixed(2)} km</p>
        </div>
        )}
      </Modal.Body>
    </Modal>
    <button onClick={handlePageBack}>Previous</button>
    <button onClick={handlePageForward}>Next</button>
  </div>
  );
};

export default StationList;
