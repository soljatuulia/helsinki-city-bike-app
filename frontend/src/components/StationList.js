import { useSelector } from 'react-redux';

const StationList = () => {  
  const stations = useSelector(state => {
    //console.log('State in StationList' + JSON.stringify(state.station.stations));
    return state.station.stations;
  });

  //console.log('We are in StationList, here are the stations: ', stations);

  return (
    <div>
      <h2>Station list</h2>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Capacity</th>
          </tr>
        </thead>
        <tbody>
          {stations.content.map(station => (
            <tr key={station.stationId}>
              <td>{station.name}</td>
              <td>{station.address}</td>
              <td>{station.capacity}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default StationList;