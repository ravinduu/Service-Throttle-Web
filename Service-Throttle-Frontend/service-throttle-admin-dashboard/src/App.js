import "./App.css";
import React, { useState, useEffect } from "react";
import axios from "axios";
import Login from "./components/login/Login";
import Home from "./components/home/Home";
import { useDataLayerValue } from "./dataLayer/DataLayer";

const api = "http://localhost:8081/st";

function App() {
  const [{ user, token }, dispatch] = useDataLayerValue();
  const [_user, setUser] = useState("");

  let authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "text/plain",
    },
  });

  useEffect(() => {
    if (token) {
      // async function fetchData() {
      const res = authAxios.get(`/account`);
      setUser({ _user, _user: res.data });
      console.log("thissss" + _user);

      dispatch({
        type: "SET_USER",
        user: _user,
      });

      console.log("thissss" + user);
    }
    // fetchData();
    // }
  }, []);
  console.log("thissss" + user);

  return <div className="App">{token ? <Home /> : <Login />}</div>;
}

export default App;
