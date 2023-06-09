import { Row, Col } from 'react-bootstrap';

import bicycleImg from '../bicycle-woman.png';

const Home = () => {
	return(
		<div>
			<Row>
				<Col>
					<img src={bicycleImg} />
				</Col>
				<Col>
					<h1>Helsinki Bike App</h1>
					<p>Here you can view data from journeys made with city bikes 
        in the Helsinki Capital area in summer 2021.</p>
					<p>Choose journeys or stations from the menu.</p>
				</Col>
			</Row>
		</div>
	);
};

export default Home;