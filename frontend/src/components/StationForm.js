import { useState } from 'react';
import { useDispatch } from 'react-redux';

import { Button, Form, Row, Col } from 'react-bootstrap';

import { createStation } from '../reducers/stationReducer';
import { setNotification } from '../reducers/notificationReducer';

const StationForm = () => {

	const dispatch = useDispatch();

	const [stationId, setStationId] = useState('');
	const [finnishName, setFinnishName] = useState('');
	const [swedishName, setSwedishName] = useState('');
	const [finnishAdddress, setFinnishAddress] = useState('');
	const [swedishAddress, setSwedishAddress] = useState('');
	const [finnishCity, setFinnishCity] = useState('');
	const [swedishCity, setSwedishCity] = useState('');
	const [operator, setOperator] = useState('');
	const [capacity, setCapacity] = useState('');
	const [x, setX] = useState('');
	const [y, setY] = useState('');

	const createStationObject = (event) => {
		event.preventDefault();

		if (Number(stationId) < 0 || Number(capacity) < 0 || Number(x) < 0 || Number(y) < 0) {
			dispatch(setNotification('No negative numbers allowed!', 5));
			console.log('Invalid input: negative numbers not allowed.');
			return;
		} 

		addNewStation({
			journeyStationId: stationId,
			name: finnishName,
			nameSwedish: swedishName,
			address: finnishAdddress,
			addressSwedish: swedishAddress,
			city: finnishCity,
			citySwedish: swedishCity,
			operator: operator,
			capacity: capacity,
			x: x,
			y: y
		});

		setStationId('');
		setFinnishName('');
		setSwedishName('');
		setFinnishAddress('');
		setSwedishAddress('');
		setFinnishCity('');
		setSwedishCity('');
		setOperator('');
		setCapacity('');
		setX('');
		setY('');
	};

	const addNewStation = async (newStation) => {
		dispatch(createStation(newStation));
	};

	return (
		<div>
			<div className='content'>
				<h2 className='h2'>Add station</h2>
			</div>
			<Form>
				<Form.Group className="station-form" controlId="formId">
					<Form.Label>ID</Form.Label>
					<Form.Control type="id" placeholder="Enter unique ID" value={stationId} onChange={(e) => setStationId(e.target.value)} />
				</Form.Group>

				<Row>
					<Col>
						<Form.Group className="station-form" controlId="formFinnishName">
							<Form.Label>Name of station in Finnish</Form.Label>
							<Form.Control type="finnish-name" placeholder="Finnish name" value={finnishName} onChange={(e) => setFinnishName(e.target.value)} />
						</Form.Group>
					</Col>
					<Col>
						<Form.Group className="station-form" controlId="formSwedishName">
							<Form.Label>Name of station in Swedish</Form.Label>
							<Form.Control type="swedish-name" placeholder="Swedish name" value={swedishName} onChange={(e) => setSwedishName(e.target.value)} />
						</Form.Group>
					</Col>
				</Row>

				<Row>
					<Col>
						<Form.Group className="station-form" controlId="formFinnishAddress">
							<Form.Label>Address in Finnish</Form.Label>
							<Form.Control type="finnish-address" placeholder="Finnish address" value={finnishAdddress} onChange={(e) => setFinnishAddress(e.target.value)}/>
						</Form.Group>
					</Col>
					<Col>
						<Form.Group className="station-form" controlId="formSwedishAddress">
							<Form.Label>Address in Swedish</Form.Label>
							<Form.Control type="swedish-address" placeholder="Swedish address" value={swedishAddress} onChange={(e) => setSwedishAddress(e.target.value)} />
						</Form.Group>
					</Col>
				</Row>

				<Row>
					<Col>
						<Form.Group className="station-form" controlId="formFinnishCity">
							<Form.Label>City name in Finnish</Form.Label>
							<Form.Control type="finnish-city" placeholder="Finnish city" value={finnishCity} onChange={(e) => setFinnishCity(e.target.value)} />
						</Form.Group>
					</Col>
					<Col>
						<Form.Group className="station-form" controlId="formSwedishCity">
							<Form.Label>City name in Swedish</Form.Label>
							<Form.Control type="swedish-city" placeholder="Swedish city" value={swedishCity} onChange={(e) => setSwedishCity(e.target.value)} />
						</Form.Group>
					</Col>
				</Row>

				<Row>
					<Col>
						<Form.Group className="station-form" controlId="formOperator">
							<Form.Label>Operator</Form.Label>
							<Form.Control type="operator" placeholder="Operator" value={operator} onChange={(e) => setOperator(e.target.value)} />
						</Form.Group>
					</Col>
					<Col>
						<Form.Group className="station-form" controlId="formCapacity">
							<Form.Label>Station capacity</Form.Label>
							<Form.Control type="capacity" placeholder="Capacity" value={capacity} onChange={(e) => setCapacity(e.target.value)} />
						</Form.Group>
					</Col>
				</Row>

				<Row>
					<Col>
						<Form.Group className="station-form" controlId="formX">
							<Form.Label>Location (longitude)</Form.Label>
							<Form.Control type="x" placeholder="X" value={x} onChange={(e) => setX(e.target.value)} />
						</Form.Group>
					</Col>
					<Col>
						<Form.Group className="station-form" controlId="formY">
							<Form.Label>Location (latitude)</Form.Label>
							<Form.Control type="y" placeholder="Y" value={y} onChange={(e) => setY(e.target.value)} />
						</Form.Group>
					</Col>
				</Row>

				<Button className="button-add" variant="warning" type="submit" onClick={createStationObject}>
          Add station
				</Button>
			</Form>
		</div>
	);

};

export default StationForm;