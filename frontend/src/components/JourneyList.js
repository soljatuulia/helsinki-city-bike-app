import { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Button, Table } from 'react-bootstrap';

import { initializeJourneys } from '../reducers/journeyReducer';

const JourneyList = () => {
  const [currentPage, setCurrentPage] = useState(0);  
  const [sortColumn, setSortColumn] = useState(null);
  const [sortOrder, setSortOrder] = useState(null);
  const journeys = useSelector(state =>  state.journey.journeys);

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(initializeJourneys(currentPage, sortColumn, sortOrder));
  }, [currentPage, sortColumn, sortOrder, dispatch]);

  const handlePageForward = () => {
    setCurrentPage((page) => page + 1);
  };

  const handlePageBack = () => {
    if (currentPage > 0) {
        setCurrentPage((page) => page - 1);
    }
  };

  const handleSort = column => {
    if (sortColumn === column) {
      setSortOrder(order => (order === 'asc' ? 'desc' : 'asc'));
    } else {
      setSortColumn(column);
      setSortOrder('asc');
    }
  };

  return (
    <div style={{ width: '90%', margin: '0 auto' }}>
      <div style={{ textAlign: 'center' }}>
        <h2 style={{ margin: '20px auto' }}>Journeys</h2>
        <p>To sort the journeys, click on column names.</p>
      </div>
      <Table 
        variant='default'
        style={{ width:'100%', margin: '20px auto' }}
        striped
        hover
        responsive>
        <thead>
          <tr>
          <th onClick={() => handleSort('departureStationName')}>
              Departure station {sortColumn === 'departureStationName' && <i className={`bi bi-caret-${sortOrder === 'asc' ? 'up' : 'down'}`} />}
            </th>
            <th onClick={() => handleSort('returnStationName')}>
              Return station {sortColumn === 'returnStationName' && <i className={`bi bi-caret-${sortOrder === 'asc' ? 'up' : 'down'}`} />}
            </th>
            <th onClick={() => handleSort('distanceInKm')}>
              Distance (km) {sortColumn === 'distanceInKm' && <i className={`bi bi-caret-${sortOrder === 'asc' ? 'up' : 'down'}`} />}
            </th>
            <th onClick={() => handleSort('durationInMin')}>
              Duration (min) {sortColumn === 'durationInMin' && <i className={`bi bi-caret-${sortOrder === 'asc' ? 'up' : 'down'}`} />}
            </th>
          </tr>
        </thead>
        <tbody>
          {journeys.content.map(journey => (
            <tr key={journey[0]}>
              <td>{journey[3]}</td>
              <td>{journey[4]}</td>
              <td>{journey[5].toFixed(2)}</td>
              <td>{journey[6].toFixed(2)}</td>
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