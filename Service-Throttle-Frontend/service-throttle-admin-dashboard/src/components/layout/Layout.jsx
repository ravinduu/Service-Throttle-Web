import React, { useEffect } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import axios from "axios";

import "./Layout.css";
import * as vehicleService from "../../services/vehicleService";

import Header from "../header/Header";
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
import Promotion from "../../pages/promotions/Promotion";
import SrService from "../../pages/srservices/SrService";

import { useDataLayerValue } from "../../dataLayer/DataLayer";
import SrRequests from "../../pages/srrequests/SrRequests";

function Layout() {
  const [{ api }, dispatch] = useDataLayerValue();

  useEffect(() => {
    const _token = localStorage.getItem("AUTH_TOKEN");
    const _username = localStorage.getItem("USERNAME");

    dispatch({
      type: "SET_TOKEN",
      token: _token,
    });

    let authAxios = axios.create({
      baseURL: api,
      headers: {
        Authorization: `Bearer ${_token}`,
        "Content-Type": "text/plain",
      },
    });

    authAxios.get(`/account/${_username}`).then((res) => {
      dispatch({
        type: "SET_USER",
        user: res.data,
      });
    });

    vehicleService.getVehicleParts(authAxios, "make").then((res) => {
      console.log(res);
      dispatch({
        type: "SET_MAKE",
        make: res,
      });
    });
  }, []);

  return (
    <Router>
      <div className="dashbord_body">
        <Sidebar />
        <div className="body-component">
          <Header />
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

            <Route path="/st/promotions" component={Promotion} />
            <Route path="/st/vehicle/services" component={SrService} />

            <Route path="/st/footer" component={Footer} />
            <Route path="/st/service-request" component={SrRequests} />
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default Layout;
