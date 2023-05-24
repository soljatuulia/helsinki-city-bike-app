import axios from 'axios';

const baseUrl = 'http://localhost:8081/api/journeys';

const getAll = async (page, column, order, day, month) => {
  try {
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
  } catch (error) {
    console.error('Error occurred while fetching all journeys:', error);
    throw error;
  }
};

// eslint-disable-next-line import/no-anonymous-default-export
export default { getAll };
