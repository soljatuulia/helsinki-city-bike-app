import axios from 'axios';

const baseUrl = 'http://localhost:8081/api/stations';

const getAll = async () => {
  const response = await axios.get(baseUrl);
  return response.data;
};

const getStationDetails = async (id) => {
  const response = await axios.get(`${baseUrl}/${id}`);
  return response.data;
};

// eslint-disable-next-line import/no-anonymous-default-export
export default { getAll, getStationDetails };