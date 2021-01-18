import React, { useState, useEffect } from "react";
import axios from "axios";
import { useDataLayerValue } from "../../dataLayer/DataLayer";
import "./Layout.css";
import NavBar from "../navbar/NavBar";
import Sidebar from "../sidebar/Sidebar";

function Layout() {
  const [{ user, token, api }, dispatch] = useDataLayerValue();

  let authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "text/plain",
    },
  });

  useEffect(async () => {
    if (token) {
      await authAxios.get(`/account`).then((res) => {
        dispatch({
          type: "SET_USER",
          user: res.data,
        });
      });
    } else console.log("no token");
  }, []);

  return (
    <div className="dashbord_body">
      <Sidebar />
      <NavBar />
      <h2>body</h2>
      <h1>footr</h1>
    </div>
  );
}

export default Layout;
