import { createSlice } from '@reduxjs/toolkit';
import stationService from '../services/stationService';

const initialState = {
  stations: {
    content: [],
    totalPages: 0,
    totalElements: 0,
    pageable: {}
  }
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
    }
  }
});

export const initializeStations = () => {
  return async dispatch => {
    const stations = await stationService.getAll();
    const payload = {
      content: stations.content,
      totalPages: stations.totalPages,
      totalElements: stations.totalElements,
      pageable: stations.pageable
    };

    dispatch(setStations(payload));
  };
};


export const { setStations } = stationSlice.actions;
export default stationSlice.reducer;