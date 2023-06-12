import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Form, Table, NavLink, Spinner } from 'react-bootstrap';

import '../custom.css';

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
			dispatch(setNotification('No stations found!', 5));
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
		<div className='content'>
			<div>
				<h2 className='h2'>Stations</h2>
				<p>Search for stations by typing the name or address below.<br />
        Click on <i>view more</i> to view more information.<br />
        You can also <Link to="/addstation">add a new station</Link>.</p>
			</div>
			<Form>
				<Form.Control id='searchbar' placeholder="Type to start search" onChange={handleFiltering}/>
			</Form>
			<Notification />
			{isLoading ? (
				<div className='spinner'>
					<Spinner animation='border' role='status'>
						<span className='visually-hidden'>Loading...</span>
					</Spinner>
				</div>
			) : (
				<Table 
					variant='default'
					className='table'
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
										className='view-more'
										id='stationDetails'
										value={station.stationId}
										onClick={() => handleShowDetails(station.stationId)}
									>
                  view more
									</NavLink>
								</td>
							</tr>
						))}
					</tbody>
				</Table>
			)}
			<StationModal stationDetails={stationDetails} handleCloseDetails={handleCloseDetails} />
			<div className='button-group'>
				<Button 
					className='button' 
					variant='outline-warning' 
					onClick={handlePageBack}>
        Previous</Button>
				<Button 
					className='button'  
					variant='outline-warning' 
					onClick={handlePageForward}>
        Next</Button>
			</div>
		</div>
	);
};

export default StationList;