import { configureStore } from "@reduxjs/toolkit";
import UserSliceReducer from "./reducer/UserSlice.reducer";

export const store = configureStore({
  reducer: {
    user: UserSliceReducer,
  },
});

export const dispatch = store.dispatch;
export const getState = store.getState;
