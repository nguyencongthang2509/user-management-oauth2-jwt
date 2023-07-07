import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  data: [],
  currentPage: 0,
  totalPages: 0,
};

const UserSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    SetUsers: (state, action) => {
      state.data = action.payload;
    },
  },
});

export const { SetUsers } = UserSlice.actions;

export const GetUsers = (state) => state.user.data;

export default UserSlice.reducer;
