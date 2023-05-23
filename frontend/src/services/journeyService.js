import axios from 'axios';

const baseUrl = 'http://localhost:8081/api/journeys';

const getAll = async (page) => {
  const response = await axios.get(baseUrl, {
    params: {
      page: page
    }
  });
  return response.data;
};

// eslint-disable-next-line import/no-anonymous-default-export
export default { getAll };