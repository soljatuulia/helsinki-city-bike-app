import { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';

import StationList from './components/StationList';

import stations from './services/stationService';
import { initializeStations } from './reducers/stationReducer';

const App = () => {
  const dispatch = useDispatch();
  const [loading, setLoading] = useState(true);

  console.log('Before useEffect');

  useEffect(() => {
    console.log('In useEffect');
    dispatch(initializeStations(stations));
    setLoading(false);
  }, [dispatch]);

  return (
    <div>
    {loading ? (
    <p>Loading...</p>
    ) : (
    <StationList />
    )}
    </div>
    );
    };

    export default App;