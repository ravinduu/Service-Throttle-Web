import React, { useState, useEffect } from "react";
import axios from "axios";
import { useDataLayerValue } from "../../../dataLayer/DataLayer";
import * as vehicleService from "../../../services/vehicleService";
import VehicleListTable from "../VehicleListTable";

const headCells = [
  { id: "mobilemechanic", label: "Mechanic Username" },
  { id: "make", label: "Make" },
  { id: "model", label: "Model" },
  { id: "year", label: "Year" },
  { id: "engine", label: "Engine" },
  { id: "capacity", label: "Capacity" },
  { id: "actions", label: "Actions", disableSorting: true },
];

function MobileServiceVehicle() {
  const [type, setType] = useState("mobile-service-vehicle");

  return (
    <div>
      <VehicleListTable
        headCells={headCells}
        type={type}
        title="Service Vehcicle List"
      ></VehicleListTable>
    </div>
  );
}

export default MobileServiceVehicle;
