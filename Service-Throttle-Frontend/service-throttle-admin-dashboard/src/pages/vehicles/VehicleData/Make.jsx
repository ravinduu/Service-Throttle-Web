import React from "react";
import VehiclePartsList from "../VehiclePartsList";

const headCells = [
  { id: "make", label: "Make List" },
  { id: "actions", label: "Actions", disableSorting: true },
];
const partType = "make";

function Make() {
  return (
    <div>
      <VehiclePartsList
        headCells={headCells}
        partType={partType}
      ></VehiclePartsList>
    </div>
  );
}

export default Make;
