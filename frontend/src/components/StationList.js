import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Form, Table, NavLink, Spinner } from 'react-bootstrap';

import Notification from './Notification';
import StationModal from './StationModal';

import { fetchStationDetails, initializeStations, setSelectedStationDetails } from '../reducers/stationReducer';
import { setNotification } from '../reducers/notificationReducer';

const StationList = () => {
	const [isLoading, setIsLoading] = useState(true);
	const [currentPage, setCurrentPage] = useState(0);
	const [filter, setFilter] = useState('');

	const stations = useSelector((state) => state.station.stations);
	const stationDetails = useSelector((state) => state.station.stationDetails);

	const dispatch = useDispatch();

	useEffect(() => {
		setIsLoading(true);

		dispatch(initializeStations(currentPage, filter))
			.then(() => setIsLoading(false));
	}, [currentPage, filter, dispatch]);

	const handleFiltering = (event) => {
		setFilter(event.target.value);

		if (event.target.value === '' && stations.content.length === 0) {
			dispatch(setNotification('No stations found', 5));
		}
	};

	const handlePageForward = () => {
		setCurrentPage((page) => page + 1);
	};

	const handlePageBack = () => {
		if (currentPage > 0) {
			setCurrentPage((page) => page - 1);
		} 
	};

	const handleShowDetails = (stationId) => {
		dispatch(fetchStationDetails(stationId));
	};

	const handleCloseDetails = () => {
		dispatch(setSelectedStationDetails(null));
	};

	return (
		<div style={{ width: '90%', margin: '0 auto' }}>
			<div style={{ textAlign: 'center' }}>
				<h2 style={{ margin: '20px auto' }}>Stations</h2>
				<p>Search for stations by typing the name or address below.<br />
        Click on <i>station details</i> to view more information.</p>
			</div>
			<Form>
				<Form.Control id='searchbar' onChange={handleFiltering}/>
			</Form>
			<Notification />
			{isLoading ? (
				<div style={{ textAlign: 'center', margin: '20px' }}>
					<Spinner animation="border" role="status">
						<span className="visually-hidden">Loading...</span>
					</Spinner>
				</div>
			) : (
				<Table 
					variant='default'
					style={{ width:'100%', margin: '20px auto' }}
					striped
					hover
					responsive>
					<thead>
						<tr>
							<th>Name</th>
							<th>Address</th>
							<th>Info</th>
						</tr>
					</thead>
					<tbody>
						{stations.content.map((station) => (
							<tr key={station.stationId}>
								<td>{station.name}</td>
								<td>{station.address}</td>
								<td>
									<NavLink
										id='stationDetails'
										value={station.stationId}
										onClick={() => handleShowDetails(station.stationId)}
										style={{ color: 'DarkGoldenRod' }}
									>
                  station details
									</NavLink>
								</td>
							</tr>
						))}
					</tbody>
				</Table>
			)}
			<StationModal stationDetails={stationDetails} handleCloseDetails={handleCloseDetails} />
			<div style={{ display: 'flex', justifyContent: 'space-between', margin: 'auto auto 20px auto' }}>
				<Button variant='outline-warning' onClick={handlePageBack}>Previous</Button>
				<Button variant='outline-warning' onClick={handlePageForward}>Next</Button>
			</div>
		</div>
	);
};

export default StationList;
