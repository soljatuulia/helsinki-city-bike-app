import { createSlice } from '@reduxjs/toolkit';

import journeyService from '../services/journeyService';
import { setNotification } from './notificationReducer';

const initialState = {
	journeys: {
		content: [],
		totalPages: 0,
		totalElements: 0,
		pageable: {}
	}
};

const journeySlice = createSlice({
	name: 'journeys', 
	initialState,
	reducers: {
		setJourneys(state, action) {
			const { content, totalPages, totalElements, pageable } = action.payload;
			console.log('setJourneys content:', content);
			console.log('setJourneys totalPages:', totalPages);
			console.log('setJourneys totalElements:', totalElements);
			console.log('setJourneys pageable:', pageable);
      
			return {
				...state,
				journeys: {
					content,
					totalPages,
					totalElements,
					pageable
				}
			};
		}
	}
});

export const initializeJourneys = (page, column, order, day, month) => {
	return async dispatch => {
		try {
			const journeys = await journeyService.getAll(page, column, order, day, month);
			const payload = {
				content: journeys.content,
				totalPages: journeys.totalPages,
				totalElements: journeys.totalElements,
				pageable: journeys.pageable
			};
			dispatch(setJourneys(payload));

			if (journeys.content.length === 0) {
				dispatch(setNotification('No stations found', 5));
			}
		} catch (error) {
			console.error('Error occurred while initializing journeys:', error);
		}
	};
};

export const { setJourneys } = journeySlice.actions;
export default journeySlice.reducer;