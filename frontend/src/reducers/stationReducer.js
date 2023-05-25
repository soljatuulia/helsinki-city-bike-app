import { createSlice } from '@reduxjs/toolkit';

import stationService from '../services/stationService';
import { setNotification } from './notificationReducer';

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

export const initializeStations = (page, filter) => {
	return async (dispatch) => {
		try {
			const stations = await stationService.getAll(page, filter);
			const payload = {
				content: stations.content,
				totalPages: stations.totalPages,
				totalElements: stations.totalElements,
				pageable: stations.pageable
			};

			dispatch(setStations(payload));

			if (stations.content.length === 0) {
				dispatch(setNotification('No stations found', 5)); 
			}
		} catch (error) {
			console.error('Error occurred while initializing stations:', error);
		}
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