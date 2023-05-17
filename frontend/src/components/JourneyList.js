import { useSelector } from 'react-redux';
import Table from 'react-bootstrap/Table';

const JourneyList = () => {  
  const journeys = useSelector(state => {
    console.log('State in JourneyList' + JSON.stringify(state.journey.journeys));
    return state.journey.journeys;
  });

  console.log('JourneyList journeys: ' + journeys.content);

  return (
    <div style={{ width: "90%", margin: "0 auto" }}>
      <h2 style={{ textAlign: "center", margin: "20px auto auto" }}>Journeys</h2>
      <Table 
        variant="default"
        style={{ width:"100%", margin: "20px auto" }}
        striped
        hover
        responsive>
        <thead>
          <tr>
            <th>Departure station</th>
            <th>Return station</th>
            <th>Distance (km)</th>
            <th>Duration (min)</th>
          </tr>
        </thead>
        <tbody>
          {journeys.content.map(journey => (
            <tr key={journey[0]}>
              <td>{journey[1]}</td>
              <td>{journey[2]}</td>
              <td>{journey[3].toFixed(2)}</td>
              <td>{journey[4].toFixed(2)}</td>
            </tr>
          ))}
        </tbody>
      </Table>  
    </div>
  );
};

export default JourneyList;