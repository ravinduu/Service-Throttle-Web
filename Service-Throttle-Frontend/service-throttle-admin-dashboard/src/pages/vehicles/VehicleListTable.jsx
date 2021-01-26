import React, { useState } from "react";
import DisplayList from "../../components/table/DisplayList";

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
  { id: "username", label: "Customer username" },
  { id: "make", label: "Make" },
  { id: "model", label: "Model" },
  { id: "year", label: "Year" },
  { id: "engine", label: "Engine" },
  { id: "actions", label: "Actions", disableSorting: true },
];

function VehicleListTable(props) {
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
        title={props.title}
        data={props.data}
      >
        {props.data.map((vehicle) => {
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
        })}
      </DisplayList>
    </div>
  );
}

export default VehicleListTable;
