export const initialState = {
  username: null,
  user: null,
  token: null,
  isLoading: false,
  // api: "https://servicethrottle.herokuapp.com/st",
  api: "http://192.168.1.102:8081/st",
};

export const reducer = (state, action) => {
  console.log(action);
  switch (action.type) {
    case "SET_USER":
      return {
        ...state,
        user: action.user,
      };

    case "SET_USERNAME":
      return {
        ...state,
        username: action.username,
      };

    case "SET_TOKEN":
      return {
        ...state,
        token: action.token,
      };

    case "SET_LOADING":
      return {
        ...state,
        isLoading: action.isLoading,
      };

    default:
      return state;
  }
};
