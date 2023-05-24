import axios from 'axios';

const baseUrl = 'http://localhost:8081/api/stations';

const getAll = async (page, filter) => {
  try {
    const response = await axios.get(baseUrl, {
      params: {
        page: page,
        filter: filter
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error occurred while fetching stations:', error);
    throw error;
  }
};

const getStationDetails = async (id) => {
  try {
    const response = await axios.get(`${baseUrl}/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error occurred while fetching station details for ID ${id}:`, error);
    throw error;
  }
};

// eslint-disable-next-line import/no-anonymous-default-export
export default { getAll, getStationDetails };
