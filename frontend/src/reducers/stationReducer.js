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
		},
		appendStation(state, action) {
			console.log('appendStation: ', state.stations.content);
			state.stations.content.push(action.payload);
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

export const createStation = (object) => {
	console.log('createStation', object);
	return async dispatch => {
		try {
			const newStation = await stationService.addStation(object);
			dispatch(appendStation(newStation));
			console.log('dispatch: ', newStation);
		} catch (error) {
			console.log('Error adding station: ', error);
		}
	};
};

export const { setStations, setSelectedStationDetails, appendStation } = stationSlice.actions;
export default stationSlice.reducer;