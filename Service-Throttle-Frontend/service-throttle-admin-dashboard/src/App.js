import "./App.css";
import React, { useEffect, useState } from "react";
import Login from "./components/login/Login";
import Layout from "./components/layout/Layout";
import { useDataLayerValue } from "./dataLayer/DataLayer";

function App() {
  const [{ token }, dispatch] = useDataLayerValue();
  const authToken = localStorage.getItem("AUTH_TOKEN");

  const [notLogged, setIsLogged] = useState(!authToken);

  useEffect(() => {
    if (!notLogged) {
      dispatch({
        type: "SET_TOKEN",
        token: authToken,
      });
    }
  }, []);

  return <div className="App">{token ? <Layout /> : <Login />}</div>;
}

export default App;
