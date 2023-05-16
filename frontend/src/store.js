import { configureStore } from '@reduxjs/toolkit';

import stationReducer from './reducers/stationReducer';
import journeyReducer from './reducers/journeyReducer';

export default configureStore({
  reducer: {
    station: stationReducer,
    journey: journeyReducer
  }
});