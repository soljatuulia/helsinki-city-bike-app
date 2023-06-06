import { Link } from 'react-router-dom';
import { Navbar, Nav } from 'react-bootstrap';

import '../custom.css';

const Navigation = () => {
	return (
		<div>
			<Navbar collapseOnSelect expand='md' bg='warning' variant='light'>
				<Navbar.Brand
					className='nav-main'
					as={Link}
					to='/' 
					href='#home'
				>Helsinki Bike App
				</Navbar.Brand>
				<Navbar.Toggle aria-controls='responsive-navbar-nav' />
				<Navbar.Collapse id='responsive-navbar-nav'>
					<Nav className='nav-links'>
						<Nav.Link
							as={Link} 
							to='/journeys'
							href='#journeys' 
						>journeys
						</Nav.Link>
						<Nav.Link
							as={Link}
							to='/stations' 
							href='#stations' 
						>stations
						</Nav.Link>
					</Nav>
				</Navbar.Collapse>
			</Navbar>

		</div>
	);

};

export default Navigation;