import { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import {
  BrowserRouter as Router,
  Routes, Route, Link
} from 'react-router-dom';

import Menu from './components/Menu';
import Start from './components/Start';
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
      <Menu /><br />
      
      <Routes>
        <Route path="/" element={<Start />} />
        <Route path="/journeys" element={<JourneyList />} />
        <Route path="/stations" element={<StationList />} />
      </Routes>

    </div>
    );
    };

    export default App;