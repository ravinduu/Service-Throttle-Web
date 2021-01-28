import React, { useState, useEffect } from "react";
import axios from "axios";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import "./Layout.css";

import { useDataLayerValue } from "../../dataLayer/DataLayer";
import NavBar from "../navbar/NavBar";
import Sidebar from "../sidebar/Sidebar";
import Dashboard from "../dashboard/Dashboard";
import Footer from "../footer/Footer";
import Admins from "../../pages/users/admin/Admins";
import Customers from "../../pages/users/customer/Customers";
import Mobilemechanics from "../../pages/users/mobilemechanic/Mobilemechanics";
import Supervisors from "../../pages/users/supervisor/Supervisors";
import CustomerVehicle from "../../pages/vehicles/customerVehicles/CustomerVehicle";
import MobileServiceVehicle from "../../pages/vehicles/mobileServiceVehicles/MobileServiceVehicle";
import Make from "../../pages/vehicles/VehicleData/Make";
import Model from "../../pages/vehicles/VehicleData/Model";
import Engine from "../../pages/vehicles/VehicleData/Engine";

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
          <Route
            path="/st/vehicles/customer-vehicle"
            exact
            component={CustomerVehicle}
          />
          <Route
            path="/st/vehicles/mobile-service-vehicle"
            exact
            component={MobileServiceVehicle}
          />
          <Route path="/st/vehicles/make" exact component={Make} />
          <Route path="/st/vehicles/model" exact component={Model} />
          <Route path="/st/vehicles/engine" exact component={Engine} />

          <Route path="/st/navbar" component={NavBar} />
          <Route path="/st/footer" component={Footer} />
        </Switch>
      </div>
    </Router>
  );
}

export default Layout;
