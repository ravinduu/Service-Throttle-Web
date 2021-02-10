import React, { useState, useEffect } from "react";
import axios from "axios";
import DisplayList from "../../components/table/DisplayList";
import { useDataLayerValue } from "../../dataLayer/DataLayer";
import * as vehicleService from "../../services/vehicleService";
import Popup from "../../components/popup/Popup";
import EditVehicleParts from "../../components/form/EditVehicleParts";
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

function VehiclePartsList(props) {
  const [{ token, api }] = useDataLayerValue();

  const authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  const [vehicleParts, setVehicleParts] = useState([]);

  const { headCells, partType } = props;
  const classes = useStyles();
  const type = useState("engine");

  const [recordForAdd, setRecordForAdd] = useState(null);
  const [recordForEdit, setRecordForEdit] = useState(null);
  const [recordForDelete, setRecordForDelete] = useState(null);
  const [openPopupEdit, setOpenPopupEdit] = useState(false);
  const [openPopupDelete, setOpenPopupDelete] = useState(false);
  const [openPopupAdd, setOpenPopupAdd] = useState(false);
  const [timesReload, setTimesReload] = useState(0);

  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const fetchVehicleParts = async () => {
    await vehicleService
      .getVehicleParts(authAxios, partType)
      .then((res) => {
        setVehicleParts(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    if (token) {
      fetchVehicleParts();
    } else console.log("no token");
  }, [timesReload]);

  const editVehiclePart = (vehiclePart) => {
    vehicleService
      .updateVehicleParts(authAxios, vehiclePart, partType)
      .then(() => {
        setOpenPopupEdit(false);
        setTimesReload(timesReload + 1);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const deleteVehiclePart = (vehiclePart) => {
    vehicleService
      .deleteVehicleParts(authAxios, vehiclePart, partType)
      .then(() => {
        setOpenPopupDelete(false);
        setTimesReload(timesReload + 1);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const addVehiclePart = (vehiclePart) => {
    console.log(vehiclePart);
    vehicleService
      .addVehicleParts(authAxios, vehiclePart, partType)
      .then(() => {
        setOpenPopupAdd(false);
        setTimesReload(timesReload + 1);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const addBtn = () => {
    return (
      <Button
        variant="outlined"
        startIcon={<AddIcon />}
        className={classes.newButton}
        onClick={() => {
          if (partType === "engine") {
            setRecordForAdd({ engine: "" });
          } else if (partType === "make") {
            setRecordForAdd({ make: "" });
          } else {
            setRecordForAdd({ make: "", model: "" });
          }

          setOpenPopupAdd(true);
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
        page={page}
        rowsPerPage={rowsPerPage}
        handleChangePage={handleChangePage}
        handleChangeRowsPerPage={handleChangeRowsPerPage}
      >
        {(rowsPerPage > 0
          ? vehicleParts.slice(
              page * rowsPerPage,
              page * rowsPerPage + rowsPerPage
            )
          : vehicleParts
        ).map((vehiclePart) => {
          if (partType === "engine") {
            return (
              <React.Fragment>
                <TableRow key={vehiclePart.id}>
                  <TableCell>{vehiclePart.engine}</TableCell>
                  <TableCell>
                    <IconButton
                      color="primary"
                      onClick={async () => {
                        await setRecordForEdit(vehiclePart);
                        setOpenPopupEdit(true);
                      }}
                    >
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
                <TableRow key={vehiclePart.id}>
                  <TableCell>{vehiclePart.make}</TableCell>
                  <TableCell>
                    <IconButton
                      color="primary"
                      onClick={async () => {
                        await setRecordForEdit(vehiclePart);
                        console.log(vehiclePart);
                        setOpenPopupEdit(true);
                      }}
                    >
                      <EditOutlinedIcon fontSize="small" />
                    </IconButton>
                    <IconButton
                      color="secondary"
                      onClick={async () => {
                        await setRecordForDelete(vehiclePart);
                        setOpenPopupDelete(true);
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
              <TableRow key={vehiclePart.id}>
                <TableCell>{vehiclePart.vehicleMake.make}</TableCell>
                <TableCell>{vehiclePart.model}</TableCell>
                <TableCell>
                  <IconButton
                    color="primary"
                    onClick={async () => {
                      await setRecordForEdit(vehiclePart);
                      console.log(vehiclePart);
                      setOpenPopupEdit(true);
                    }}
                  >
                    <EditOutlinedIcon fontSize="small" />
                  </IconButton>
                  <IconButton color="secondary" onClick={console.log()}>
                    <CloseIcon fontSize="small" />
                  </IconButton>
                </TableCell>
              </TableRow>
            </React.Fragment>
          );
        })}
      </DisplayList>
      <Popup
        title={partType}
        openPopup={openPopupEdit}
        setOpenPopup={setOpenPopupEdit}
      >
        <EditVehicleParts
          editVehiclePart={editVehiclePart}
          recordForEdit={recordForEdit}
          partType={partType}
        />
      </Popup>
      <Popup
        title="Delete"
        openPopup={openPopupDelete}
        setOpenPopup={setOpenPopupDelete}
      >
        <Delete
          deleteRecord={deleteVehiclePart}
          recordForDelete={recordForDelete}
          setOpenPopupDelete={setOpenPopupDelete}
          name={partType}
        />
      </Popup>
      <Popup
        title={partType}
        openPopup={openPopupAdd}
        setOpenPopup={setOpenPopupAdd}
      >
        <EditVehicleParts
          editVehiclePart={addVehiclePart}
          recordForEdit={recordForAdd}
          partType={partType}
        />
      </Popup>
    </div>
  );
}

export default VehiclePartsList;
