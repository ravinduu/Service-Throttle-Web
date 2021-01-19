import React, { useState, useEffect } from "react";
import axios from "axios";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import "./Layout.css";

import { useDataLayerValue } from "../../dataLayer/DataLayer";
import NavBar from "../navbar/NavBar";
import Sidebar from "../sidebar/Sidebar";
import Dashboard from "../dashboard/Dashboard";
import Footer from "../footer/Footer";
import Admins from "../users/admin/Admins";
import Customers from "../users/customer/Customers";
import Mobilemechanics from "../users/mobilemechanic/Mobilemechanics";
import Supervisors from "../users/supervisor/Supervisors";

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
    <Router>
      <div className="dashbord_body">
        <Sidebar />
        <Switch>
          <Route path="/" exact component={Dashboard} />
          <Route path="/st/users/admins" exact component={Admins} />
          <Route path="/st/users/customers" exact component={Customers} />
          <Route path="/st/users/supervisors" exact component={Supervisors} />
          <Route
            path="/st/users/mobile-mechanics"
            exact
            component={Mobilemechanics}
          />
          <Route path="/st/navbar" component={NavBar} />
          <Route path="/st/footer" component={Footer} />
        </Switch>
      </div>
    </Router>
  );
}

export default Layout;
