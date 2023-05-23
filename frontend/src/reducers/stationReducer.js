import { createSlice } from '@reduxjs/toolkit';
import stationService from '../services/stationService';

const initialState = {
  stations: {
    content: [],
    totalPages: 0,
    totalElements: 0,
    pageable: {}
  },
  stationDetails: null
};

const stationSlice = createSlice({
  name: 'stations', 
  initialState,
  reducers: {
    setStations(state, action) {
      const { content, totalPages, totalElements, pageable } = action.payload;
      //console.log('setStations content:', content);
      //console.log('setStations totalPages:', totalPages);
      //console.log('setStations totalElements:', totalElements);
      //console.log('setStations pageable:', pageable);
      
      return {
        ...state,
        stations: {
          content,
          totalPages,
          totalElements,
          pageable
        }
      };
    },
    setSelectedStationDetails(state, action) {
      state.stationDetails = action.payload;
    }
  }
});

export const initializeStations = (page) => {
  return async dispatch => {
    const stations = await stationService.getAll(page);
    const payload = {
      content: stations.content,
      totalPages: stations.totalPages,
      totalElements: stations.totalElements,
      pageable: stations.pageable
    };

    dispatch(setStations(payload));
  };
};

export const fetchStationDetails = (stationId) => {
  return async (dispatch) => {
    try {
      const stationDetails = await stationService.getStationDetails(stationId);
      dispatch(setSelectedStationDetails(stationDetails));
    } catch (error) {
      console.log('Error fetching station details:', error);
    }
  };
};

export const { setStations, setSelectedStationDetails } = stationSlice.actions;
export default stationSlice.reducer;