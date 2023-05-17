import { useDispatch, useSelector } from 'react-redux';
import { fetchStationDetails, setSelectedStationDetails } from '../reducers/stationReducer';
import { Table, NavLink, Modal } from 'react-bootstrap';

const StationList = () => {
  const dispatch = useDispatch();
  const stations = useSelector((state) => state.station.stations);
  const stationDetails = useSelector((state) => state.station.stationDetails);

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
                >
                  station info
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
          <p><b>Total Departures:</b> {stationDetails.totalDepartures}</p>
          <p><b>Total Returns:</b> {stationDetails.totalReturns}</p>
        </div>
        )}
      </Modal.Body>
    </Modal>
  </div>
  );
};

export default StationList;
