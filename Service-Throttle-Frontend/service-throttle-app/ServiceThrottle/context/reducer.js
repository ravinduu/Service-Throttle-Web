export const initialState = {
  username: null,
  user: null,
  token: null,
  isLoading: false,
  services: [],
  promotions: [],
  engines: [],
  makes: [],
  models: [],

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

    case "SET_SERVICES":
      return {
        ...state,
        services: action.services,
      };

    case "SET_PROMOS":
      return {
        ...state,
        promotions: action.promotions,
      };

    case "SET_ENGINES":
      return {
        ...state,
        engines: action.engines,
      };
    case "SET_MAKES":
      return {
        ...state,
        makes: action.makes,
      };
    case "SET_MODELS":
      return {
        ...state,
        models: action.models,
      };

    default:
      return state;
  }
};
