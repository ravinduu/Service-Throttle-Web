export const initialState = {
  user: null,
  token: null,
  username: null,
  makes: [],
  api: "http://localhost:8081/st",
};

export const reducer = (state, action) => {
  console.log(action);
  switch (action.type) {
    case "SET_USER":
      return {
        ...state,
        user: action.user,
      };

    case "SET_TOKEN":
      return {
        ...state,
        token: action.token,
      };

    case "SET_USERNAME":
      return {
        ...state,
        username: action.username,
      };

    case "SET_MAKE":
      return {
        ...state,
        makes: action.make,
      };

    default:
      return state;
  }
};
