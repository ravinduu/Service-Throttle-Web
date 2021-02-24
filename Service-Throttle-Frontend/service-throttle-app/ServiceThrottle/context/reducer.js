export const initialState = {
  user: null,
  token: null,
  // api: "https://servicethrottle.herokuapp.com/st",
  api: "http://192.168.1.102:8081/st",
};

export const reducer = (state, action) => {
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

    default:
      return state;
  }
};
