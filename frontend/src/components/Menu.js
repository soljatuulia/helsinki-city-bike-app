import {
  BrowserRouter as Router,
  Routes, Route, Link, Navigate,
  useNavigate, useMatch, useParams
} from 'react-router-dom';

const Menu = () => {
  const padding = {
    paddingRight: 5
  };

  return (
    <div>
      <Link style={padding} to="/">start</Link>
      <Link style={padding} to="/journeys">journeys</Link>
      <Link style={padding} to="/stations">stations</Link>
    </div>
  );
};

export default Menu;