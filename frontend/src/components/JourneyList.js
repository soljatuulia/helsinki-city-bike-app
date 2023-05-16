import { useSelector } from 'react-redux';

const JourneyList = () => {  
  const journeys = useSelector(state => {
    console.log('State in JourneyList' + JSON.stringify(state.journey.journeys));
    return state.journey.journeys;
  });

  console.log('JourneyList journeys: ' + journeys.content);

  return (
    <div>
      <h2>Journey list</h2>
      <table>
        <thead>
          <tr>
            <th>Departure station</th>
            <th>Return station</th>
            <th>Journey duration</th>
            <th>Journey length</th>
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
      </table>
    </div>
  );
};

export default JourneyList;