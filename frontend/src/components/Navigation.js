import { BrowserRouter as Router, Link } from 'react-router-dom';
import { Navbar, Nav } from 'react-bootstrap';

const Menu = () => {

  return (
    <div>
      <Navbar collapseOnSelect expand="lg" bg="warning" variant="light">
        <Navbar.Brand
          as={Link}
          to="/" 
          href="#home"
          style={{ width: "200px"}}
        >Helsinki Bike App
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link
              as={Link} 
              to="/journeys"
              href="#journeys" 
              style={{ width: "100px" }}
            >journeys
            </Nav.Link>
            <Nav.Link
              as={Link}
              to="/stations" 
              href="#stations" 
              style={{ width: "100px" }}
            >stations
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>

    </div>
  )

};

export default Menu;