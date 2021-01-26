import React, { useState, useEffect } from "react";
import axios from "axios";
import { useDataLayerValue } from "../../../dataLayer/DataLayer";
import * as vehicleService from "../../../services/vehicleService";
import VehicleListTable from "../VehicleListTable";

function CustomerVehicle() {
  const [type, setType] = useState("customer-vehicle");
  const [{ token, api }] = useDataLayerValue();

  let authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "text/plain",
    },
  });

  useEffect(() => {
    if (token) {
      fetchcustomersVehicles();
    } else console.log("no token");
  }, []);

  const [customersVehicles, setcustomersVehicles] = useState([]);

  const fetchcustomersVehicles = async () => {
    const _customersVehicles = await vehicleService.getVehicles(
      authAxios,
      type
    );
    setcustomersVehicles(_customersVehicles);
  };

  return (
    <div>
      <VehicleListTable
        data={customersVehicles}
        title="Customer Vehcicle List"
      ></VehicleListTable>
    </div>
  );
}

export default CustomerVehicle;
