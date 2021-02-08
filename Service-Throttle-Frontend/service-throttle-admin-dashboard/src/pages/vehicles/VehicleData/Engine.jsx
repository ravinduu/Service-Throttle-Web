import React from "react";
import VehiclePartsList from "../VehiclePartsList";

const headCells = [
  { id: "engine", label: "Engine List" },
  { id: "actions", label: "Actions", disableSorting: true },
];
const partType = "engine";

function Engine() {
  return (
    <div>
      <VehiclePartsList
        headCells={headCells}
        partType={partType}
      ></VehiclePartsList>
    </div>
  );
}

export default Engine;
