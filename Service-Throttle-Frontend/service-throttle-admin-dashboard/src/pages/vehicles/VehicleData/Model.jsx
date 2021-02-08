import React from "react";
import VehiclePartsList from "../VehiclePartsList";

const headCells = [
  { id: "make", label: "Make " },
  { id: "model", label: "Model " },
  { id: "actions", label: "Actions", disableSorting: true },
];
const partType = "model";

function Model() {
  return (
    <div>
      <VehiclePartsList
        headCells={headCells}
        partType={partType}
      ></VehiclePartsList>
    </div>
  );
}

export default Model;
