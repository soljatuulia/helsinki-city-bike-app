import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { Routes, Route } from 'react-router-dom';
import { Container } from 'react-bootstrap';

import Navigation from './components/Navigation';
import Home from './components/Home';
import StationList from './components/StationList';
import JourneyList from './components/JourneyList';

import stations from './services/stationService';
import journeys from './services/journeyService';
import { initializeStations } from './reducers/stationReducer';
import { initializeJourneys } from './reducers/journeyReducer';

const App = () => {
	const dispatch = useDispatch();

	useEffect(() => {
		dispatch(initializeStations(stations));
		dispatch(initializeJourneys(journeys));
	}, [dispatch]);

	return (
		<div>
			<Container>
				<Navigation />
      
				<Routes>
					<Route path="/" element={<Home />} />
					<Route path="/journeys" element={<JourneyList />} />
					<Route path="/stations" element={<StationList />} />
				</Routes>

			</Container>
		</div>
	);
};

export default App;