import { createSlice } from '@reduxjs/toolkit';
import stationService from '../services/stationService';

const initialState = {
  stations: {
    content: []
  }
};

const stationSlice = createSlice({
  name: 'stations', 
  initialState,
  reducers: {
    setStations(state = initialState, action) {
      console.log('setStations action.payload.content: ', action.payload.content);
      return { stations: action.payload.content };
    }
  }
});

export const initializeStations = () => {
  return async dispatch => {
    const stations = await stationService.getAll();
    console.log('initializeStations: ', stations);
    dispatch(setStations(stations));
  };
};

export const { setStations } = stationSlice.actions;
export default stationSlice.reducer;