import React, { useState, useEffect } from "react";
import axios from "axios";
import { useDataLayerValue } from "../../../dataLayer/DataLayer";
import * as vehicleService from "../../../services/vehicleService";
import VehicleListTable from "../VehicleListTable";

function MobileServiceVehicle() {
  const [type, setType] = useState("mobile-service-vehicle");
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
      fetchservicesVehicles();
    } else console.log("no token");
  }, []);

  const [servicesVehicles, setservicesVehicles] = useState([]);

  const fetchservicesVehicles = async () => {
    const _servicesVehicles = await vehicleService.getVehicles(authAxios, type);
    setservicesVehicles(_servicesVehicles);
  };

  return (
    <div>
      <VehicleListTable
        data={servicesVehicles}
        title="Service Vehcicle List"
      ></VehicleListTable>
    </div>
  );
}

export default MobileServiceVehicle;
