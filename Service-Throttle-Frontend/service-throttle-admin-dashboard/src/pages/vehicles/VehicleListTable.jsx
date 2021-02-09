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

function VehicleListTable(props) {
  const { headCells, type, title } = props;

  const [{ token, api }] = useDataLayerValue();

  let authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
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
  const classes = useStyles();

  const [recordForEdit, setRecordForEdit] = useState(null);
  const [recordForDelete, setRecordForDelete] = useState(null);
  const [openPopup, setOpenPopup] = useState(false);

  const openInPopup = () => {
    console.log("Edittt");

    console.log(recordForEdit);
    setOpenPopup(true);
  };

  const deletevehicle = () => {
    console.log("Deletee");

    console.log(recordForDelete);
    setOpenPopup(true);
  };

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
        title={title}
        data={customersVehicles}
      >
        {customersVehicles.map((vehicle) => {
          if (type === "customer-vehicle") {
            return (
              <React.Fragment>
                <TableRow key={vehicle.id}>
                  <TableCell>{vehicle.customer.username}</TableCell>
                  <TableCell>{vehicle.vehicleMake.make}</TableCell>
                  <TableCell>{vehicle.vehicleModel.model}</TableCell>
                  <TableCell>{vehicle.year}</TableCell>
                  <TableCell>{vehicle.vehicleEngine.engine}</TableCell>
                  <TableCell>
                    <IconButton
                      color="primary"
                      onClick={async () => {
                        await setRecordForEdit(vehicle);
                        await openInPopup();
                      }}
                    >
                      <EditOutlinedIcon fontSize="small" />
                    </IconButton>
                    <IconButton
                      color="secondary"
                      onClick={async () => {
                        await setRecordForDelete(vehicle);
                        await deletevehicle();
                      }}
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
                <TableCell>
                  {vehicle.mobileMechanic
                    ? vehicle.mobileMechanic.username
                    : "not assign yet"}
                </TableCell>
                <TableCell>{vehicle.vehicleMake.make}</TableCell>
                <TableCell>{vehicle.vehicleModel.model}</TableCell>
                <TableCell>{vehicle.year}</TableCell>
                <TableCell>{vehicle.vehicleEngine.engine}</TableCell>
                <TableCell>{vehicle.capacity}</TableCell>
                <TableCell>
                  <IconButton
                    color="primary"
                    onClick={async () => {
                      await setRecordForEdit(vehicle);
                      await openInPopup();
                    }}
                  >
                    <EditOutlinedIcon fontSize="small" />
                  </IconButton>
                  <IconButton
                    color="secondary"
                    onClick={async () => {
                      await setRecordForDelete(vehicle);
                      await deletevehicle();
                    }}
                  >
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

export default VehicleListTable;
