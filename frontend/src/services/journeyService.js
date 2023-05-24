import axios from 'axios';

const baseUrl = 'http://localhost:8081/api/journeys';

const getAll = async (page, column, order, day, month) => {
  const response = await axios.get(baseUrl, {
    params: {
      page: page,
      day: day,
      month: month,
      sortColumn: column,
      sortOrder: order
    }
  });
  return response.data;
};

// eslint-disable-next-line import/no-anonymous-default-export
export default { getAll };