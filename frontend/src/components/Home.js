import { Row, Col } from 'react-bootstrap';

import bicycleImg from '../bicycle-woman.png';

const Home = () => {
  return(
    <div>
      <Row>
      <Col>
        <img src={bicycleImg} alt='Home Background' />
      </Col>
      <Col>
       <h2>Hi there!</h2>
        <p>Here you can view data from journeys made with city bikes 
        in the Helsinki Capital area in summer 2021.</p>
        <p>Choose journeys or stations from the menu.</p>
      </Col>
      </Row>
    </div>
  );
};

export default Home;