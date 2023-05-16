import { useDispatch, useSelector } from 'react-redux';
import { fetchStationDetails } from '../reducers/stationReducer';

const StationList = () => {
  const dispatch = useDispatch();
  const stations = useSelector((state) => state.station.stations);
  const stationDetails = useSelector((state) => state.station.stationDetails);

  const handleShowDetails = (stationId) => {
    dispatch(fetchStationDetails(stationId));
  };

  return (
    <div>
      <h2>Station list</h2>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Capacity</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {stations.content.map((station) => (
            <tr key={station.stationId}>
              <td>{station.name}</td>
              <td>{station.address}</td>
              <td>{station.capacity}</td>
              <td>
                <button onClick={() => handleShowDetails(station.stationId)}>
                  Show Details
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
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
