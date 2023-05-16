import { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';

import StationList from './components/StationList';
import JourneyList from './components/JourneyList';

import stations from './services/stationService';
import journeys from './services/journeyService';
import { initializeStations } from './reducers/stationReducer';
import { initializeJourneys } from './reducers/journeyReducer';

const App = () => {
  const dispatch = useDispatch();
  const [loading, setLoading] = useState(true);

  console.log('Before useEffect');

  useEffect(() => {
    console.log('In useEffect');
    dispatch(initializeStations(stations));
    dispatch(initializeJourneys(journeys));
    setLoading(false);
  }, [dispatch]);

  return (
    <div>
    {loading ? (
    <p>Loading...</p>
    ) : (
    <JourneyList />
    )}
    </div>
    );
    };

    export default App;