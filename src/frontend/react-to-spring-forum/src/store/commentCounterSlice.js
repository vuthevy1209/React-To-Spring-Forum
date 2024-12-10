import { createSlice } from '@reduxjs/toolkit';

const commentCounterSlice = createSlice({
    name: 'commentCounter',
    initialState: {
        commentCounter: {},
    },
    reducers: {
        setCounter: (state, action) => {
            state.commentCounter[action.payload.postId] = action.payload.count;
        },
        increment: (state, action) => {
            state.commentCounter[action.payload]++;
        },
        decrement: (state, action) => {
            state.commentCounter[action.payload]--;
        },
    },
});

export const { setCounter, increment, decrement } = commentCounterSlice.actions;
export const commentCounterReducer = commentCounterSlice.reducer;