import "./App.css";
import React from "react";
import Login from "./components/login/Login";
import Layout from "./components/layout/Layout";
import { useDataLayerValue } from "./dataLayer/DataLayer";

function App() {
  const [{ token }] = useDataLayerValue();

  return <div className="App">{token ? <Layout /> : <Login />}</div>;
}

export default App;
