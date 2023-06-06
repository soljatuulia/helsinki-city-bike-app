import { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Button, Table, Spinner } from 'react-bootstrap';

import '../custom.css';

import { initializeJourneys } from '../reducers/journeyReducer';
import { setNotification } from '../reducers/notificationReducer';
import JourneyDateFilter from './JourneyDateFilter';
import Notification from './Notification';

const JourneyList = () => {
	const [isLoading, setIsLoading] = useState(true);
	const [currentPage, setCurrentPage] = useState(0);  
	const [sortColumn, setSortColumn] = useState(null);
	const [sortOrder, setSortOrder] = useState(null);
	const [dayFilter, setDayFilter] = useState(null);
	const [monthFilter, setMonthFilter] = useState(null);
	const journeys = useSelector(state =>  state.journey.journeys);

	const dispatch = useDispatch();

	useEffect(() => {
		setIsLoading(true);

		dispatch(initializeJourneys(currentPage, sortColumn, sortOrder, dayFilter, monthFilter))
			.then(() => setIsLoading(false));
	}, [currentPage, sortColumn, sortOrder, dayFilter, monthFilter, dispatch]);

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

	const handleDateFilter = (day, month) => {
		setDayFilter(day);
		setMonthFilter(month);
		setCurrentPage(0);

		if (journeys.content.length === 0) {
			dispatch(setNotification('No journeys found', 5)); 
		}
	};

	return (
		<div className='content'>
			<div>
				<h2 className='h2'>Journeys</h2>
				<p>Filter journeys by date by choosing day and month below.<br />
        Sort journeys by clicking on column names.</p>
				<JourneyDateFilter onFilter={(day, month) => handleDateFilter(day, month)} />
			</div>
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
							<th 
								id='departureStation' 
								onClick={() => handleSort('departureStationName')}>
              Departure station 
								{sortColumn === 'departureStationName' && 
                <i className={`bi bi-caret-${sortOrder === 'asc' ? 'up' : 'down'}`} />}
							</th>
							<th 
								id='returnStation' 
								onClick={() => handleSort('returnStationName')}>
              Return station 
								{sortColumn === 'returnStationName' && 
                <i className={`bi bi-caret-${sortOrder === 'asc' ? 'up' : 'down'}`} />
								}
							</th>
							<th 
								id='distance' 
								onClick={() => handleSort('distanceInKm')}>
              Distance (km) 
								{sortColumn === 'distanceInKm' && 
                <i className={`bi bi-caret-${sortOrder === 'asc' ? 'up' : 'down'}`} />
								}
							</th>
							<th 
								id= 'duration' 
								onClick={() => handleSort('durationInMin')}>
              Duration (min) 
								{sortColumn === 'durationInMin' && 
                <i className={`bi bi-caret-${sortOrder === 'asc' ? 'up' : 'down'}`} />
								}
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
			)}
			<div className='page-button-group'>
				<Button 
					className='page-button' 
					variant='outline-warning' 
					onClick={handlePageBack}>
        Previous</Button>
				<Button 
					className='page-button'  
					variant='outline-warning' 
					onClick={handlePageForward}>
        Next</Button>
			</div>
		</div>
	);
};

export default JourneyList;