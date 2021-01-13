import "./App.css";
import React from "react";
import Login from "./components/login/Login";
import Home from "./components/dashboard/Dashboard";
import { useDataLayerValue } from "./dataLayer/DataLayer";

function App() {
  const [{ token }] = useDataLayerValue();

  return <div className="App">{token ? <Home /> : <Login />}</div>;
}

export default App;
