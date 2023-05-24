import { Modal } from 'react-bootstrap';

const StationModal = ({ stationDetails, handleCloseDetails }) => {
	return (
		<div>
			<Modal show={!!stationDetails} onHide={handleCloseDetails}>
				<Modal.Header closeButton>
					<Modal.Title>Station Details</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					{stationDetails && (
						<div>
							<p><b>Name:</b> {stationDetails.name}</p>
							<p><b>Address:</b> {stationDetails.address}</p>
							<p><b>Number of departures:</b> {stationDetails.totalDepartures}<br />
								<b>Number of returns:</b> {stationDetails.totalReturns}</p>
							<p><b>Average length of journeys starting here:</b> {stationDetails.averageDepartureDistance.toFixed(2)} km<br />
								<b>Average length of journeys ending here:</b> {stationDetails.averageReturnDistance.toFixed(2)} km</p>
						</div>
					)}
				</Modal.Body>
			</Modal>
		</div>
	);
};

export default StationModal;