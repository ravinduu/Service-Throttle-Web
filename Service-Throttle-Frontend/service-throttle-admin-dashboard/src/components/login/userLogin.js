import axios from "axios";
import { useDataLayerValue } from "../../dataLayer/DataLayer";

const api = "http://localhost:8081/st";
const state = {
  username: "",
  password: "",
  token: "",
};

export const UserLogin = async (username, password) => {
  state.username = username;
  state.password = password;
  return (dispatch) => {
    const res = axios.post(`${api}/login`, state);
    state.token = res.data.jwttoken;

    dispatch({
      type: "SET_TOKEN",
      token: state.token,
    });
  };

  // state.username = username;
  // state.password = password;
  // console.log("before : " + state.token);
  // try {
  //   const res = await axios.post(`${api}/login`, state);
  //   state.token = res.data.jwttoken;
  //   console.log("after : " + state.token);
  //   SetToken(state.token);
  //   return state.token;
  // } catch (err) {}
};

export const getToken = () => {
  return state.token;
};

// function SetToken(params) {
//   console.log("here " + params);
//   const [{ token }, dispatch] = useDataLayerValue();
//   const _token = params;
//   if (_token) {
//     dispatch({
//       type: "SET_TOKEN",
//       token: _token,
//     });
//   }
//   console.log(token);
// }

export default getToken;

let authAxios = axios.create({
  baseURL: api,
  headers: {
    Authorization: `Bearer ${state.token}`,
  },
});
