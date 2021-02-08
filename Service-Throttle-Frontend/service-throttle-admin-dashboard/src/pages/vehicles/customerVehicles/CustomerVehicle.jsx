import React, { useState } from "react";
import VehicleListTable from "../VehicleListTable";

const headCells = [
  { id: "username", label: "Customer username" },
  { id: "make", label: "Make" },
  { id: "model", label: "Model" },
  { id: "year", label: "Year" },
  { id: "engine", label: "Engine" },
  { id: "actions", label: "Actions", disableSorting: true },
];

function CustomerVehicle() {
  const [type] = useState("customer-vehicle");

  return (
    <div>
      <VehicleListTable
        headCells={headCells}
        type={type}
        title="Customer Vehcicle List"
      ></VehicleListTable>
    </div>
  );
}

export default CustomerVehicle;
