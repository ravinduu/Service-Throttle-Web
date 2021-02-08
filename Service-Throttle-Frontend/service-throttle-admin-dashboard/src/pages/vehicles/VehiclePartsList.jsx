import React, { useState, useEffect } from "react";
import axios from "axios";
import DisplayList from "../../components/table/DisplayList";
import { useDataLayerValue } from "../../dataLayer/DataLayer";
import * as vehicleService from "../../services/vehicleService";

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

function VehiclePartsList(props) {
  const [{ token, api }] = useDataLayerValue();

  const authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  useEffect(() => {
    if (token) {
      fetchVehicleParts();
    } else console.log("no token");
  }, []);

  const [vehicleParts, setVehicleParts] = useState([]);

  const fetchVehicleParts = async () => {
    await vehicleService
      .getVehicleParts(authAxios, partType)
      .then((res) => {
        setVehicleParts(res);
      })
      .catch((err) => {
        console.log(err);
      });

    console.log(vehicleParts);
  };

  const { headCells, partType } = props;
  const classes = useStyles();
  const type = useState("engine");

  const [recordForEdit, setRecordForEdit] = useState(null);
  const [recordForDelete, setRecordForDelete] = useState(null);
  const [openPopup, setOpenPopup] = useState(false);

  const addBtn = () => {
    return (
      <Button
        variant="outlined"
        startIcon={<AddIcon />}
        className={classes.newButton}
        onClick={() => {
          console.log("cusss");
          setOpenPopup(true);
          setRecordForEdit(null);
        }}
      >
        Add New
      </Button>
    );
  };

  return (
    <div>
      <DisplayList
        headCells={headCells}
        addBtn={addBtn()}
        title={partType + " list"}
        data={vehicleParts}
      >
        {vehicleParts.map((vehicle) => {
          if (partType === "engine") {
            return (
              <React.Fragment>
                <TableRow key={vehicle.id}>
                  <TableCell>{vehicle.engine}</TableCell>
                  <TableCell>
                    <IconButton color="primary" onClick={console.log("edit")}>
                      <EditOutlinedIcon fontSize="small" />
                    </IconButton>
                    <IconButton
                      color="secondary"
                      onClick={console.log("close")}
                    >
                      <CloseIcon fontSize="small" />
                    </IconButton>
                  </TableCell>
                </TableRow>
              </React.Fragment>
            );
          } else if (partType === "make") {
            return (
              <React.Fragment>
                <TableRow key={vehicle.id}>
                  <TableCell>{vehicle.make}</TableCell>
                  <TableCell>
                    <IconButton color="primary" onClick={console.log("edit")}>
                      <EditOutlinedIcon fontSize="small" />
                    </IconButton>
                    <IconButton
                      color="secondary"
                      onClick={console.log("close")}
                    >
                      <CloseIcon fontSize="small" />
                    </IconButton>
                  </TableCell>
                </TableRow>
              </React.Fragment>
            );
          }

          return (
            <React.Fragment>
              <TableRow key={vehicle.id}>
                <TableCell>{vehicle.vehicleMake.make}</TableCell>
                <TableCell>{vehicle.model}</TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={console.log("edit")}>
                    <EditOutlinedIcon fontSize="small" />
                  </IconButton>
                  <IconButton color="secondary" onClick={console.log("close")}>
                    <CloseIcon fontSize="small" />
                  </IconButton>
                </TableCell>
              </TableRow>
            </React.Fragment>
          );
        })}
      </DisplayList>
    </div>
  );
}

export default VehiclePartsList;
