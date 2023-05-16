import { configureStore } from '@reduxjs/toolkit';

import stationReducer from './reducers/stationReducer';

export default configureStore({
  reducer: {
    station: stationReducer
  }
});