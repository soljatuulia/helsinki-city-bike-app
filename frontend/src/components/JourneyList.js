import { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Button, Table } from 'react-bootstrap';

import { initializeJourneys } from '../reducers/journeyReducer';

const JourneyList = () => {
  const [currentPage, setCurrentPage] = useState(0);  
  const journeys = useSelector(state =>  state.journey.journeys);

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(initializeJourneys(currentPage));
  }, [currentPage, dispatch]);

  const handlePageForward = () => {
    setCurrentPage((page) => page + 1);
  };

  const handlePageBack = () => {
    if (currentPage > 0) {
        setCurrentPage((page) => page - 1);
    }
  };

  return (
    <div style={{ width: '90%', margin: '0 auto' }}>
      <h2 style={{ textAlign: 'center', margin: '20px auto auto' }}>Journeys</h2>
      <Table 
        variant='default'
        style={{ width:'100%', margin: '20px auto' }}
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
      <div style={{ display: 'flex', justifyContent: 'space-between', margin: 'auto auto 20px auto' }}>
        <Button variant='outline-warning' onClick={handlePageBack}>Previous</Button>
        <Button variant='outline-warning' onClick={handlePageForward}>Next</Button>
      </div>
    </div>
  );
};

export default JourneyList;