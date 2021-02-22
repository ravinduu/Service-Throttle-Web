import React, { useState, useEffect } from "react";
import axios from "axios";
import DisplayList from "../../components/table/DisplayList";
import { useDataLayerValue } from "../../dataLayer/DataLayer";
import Popup from "../../components/popup/Popup";
import Delete from "../../components/deleteItem/Delete";

import {
  makeStyles,
  TableRow,
  TableCell,
  IconButton,
  Button,
} from "@material-ui/core";

import AddIcon from "@material-ui/icons/Add";
import CloseIcon from "@material-ui/icons/Close";
import EditOutlinedIcon from "@material-ui/icons/EditOutlined";

const useStyles = makeStyles((theme) => ({
  newButton: {
    position: "absolute",
    right: "10px",
  },
}));

const headCells = [
  { id: "customer", label: "Customer" },
  { id: "customerVehicle", label: "Price" },
  { id: "vehicleServices", label: "Description" },
  { id: "vehicleServiceType", label: "Type" },
  { id: "imageURL", label: "Image" },
  { id: "actions", label: "Actions", disableSorting: true },
];

function SrRequests() {
  const [{ token, api }] = useDataLayerValue();

  return <div></div>;
}

export default SrRequests;
