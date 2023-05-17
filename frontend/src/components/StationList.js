import { useDispatch, useSelector } from 'react-redux';
import { fetchStationDetails } from '../reducers/stationReducer';
import { Table, Button } from 'react-bootstrap';

const StationList = () => {
  const dispatch = useDispatch();
  const stations = useSelector((state) => state.station.stations);
  const stationDetails = useSelector((state) => state.station.stationDetails);

  const handleShowDetails = (stationId) => {
    dispatch(fetchStationDetails(stationId));
  };

  return (
    <div style={{ width: "90%", margin: "0 auto" }}>
      <h2 style={{ textAlign: "center", margin: "20px auto auto" }}>Stations</h2>
      <Table 
        variant="default"
        style={{ width:"100%", margin: "20px auto" }}
        striped
        responsive>
        <thead>
          <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Capacity</th>
            <th>Details</th>
          </tr>
        </thead>
        <tbody>
          {stations.content.map((station) => (
            <tr key={station.stationId}>
              <td>{station.name}</td>
              <td>{station.address}</td>
              <td>{station.capacity}</td>
              <td>
                <Button onClick={() => handleShowDetails(station.stationId)}>
                  Show Details
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>  
      {stationDetails && (
        <div>
          <h2>Station Details</h2>
          <p>Name: {stationDetails.name}</p>
          <p>Address: {stationDetails.address}</p>
          <p>Total Departures: {stationDetails.totalDepartures}</p>
          <p>Total Returns: {stationDetails.totalReturns}</p>
        </div>
      )}
    </div>
  );
};

export default StationList;
