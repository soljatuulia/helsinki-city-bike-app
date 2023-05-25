import { configureStore } from '@reduxjs/toolkit';

import stationReducer from './reducers/stationReducer';
import journeyReducer from './reducers/journeyReducer';
import notificationReducer from './reducers/notificationReducer';

export default configureStore({
	reducer: {
		station: stationReducer,
		journey: journeyReducer,
		notification: notificationReducer
	}
});