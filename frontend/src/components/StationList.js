import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Form, Table, NavLink } from 'react-bootstrap';

import StationModal from './StationModal';
import { fetchStationDetails, initializeStations, setSelectedStationDetails } from '../reducers/stationReducer';

const StationList = () => {
	const [currentPage, setCurrentPage] = useState(0);
	const [filter, setFilter] = useState('');

	const stations = useSelector((state) => state.station.stations);
	const stationDetails = useSelector((state) => state.station.stationDetails);

	const dispatch = useDispatch();

	useEffect(() => {
		dispatch(initializeStations(currentPage, filter));
	}, [currentPage, filter, dispatch]);

	const handleFiltering = (event) => {
		setFilter(event.target.value);
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
				<Form.Control onChange={handleFiltering}/>
			</Form>
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
			<StationModal stationDetails={stationDetails} handleCloseDetails={handleCloseDetails} />
			<div style={{ display: 'flex', justifyContent: 'space-between', margin: 'auto auto 20px auto' }}>
				<Button variant='outline-warning' onClick={handlePageBack}>Previous</Button>
				<Button variant='outline-warning' onClick={handlePageForward}>Next</Button>
			</div>
		</div>
	);
};

export default StationList;
