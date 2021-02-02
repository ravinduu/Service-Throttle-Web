import React, { useState, useEffect } from "react";
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
import EditUser from "../../components/form/EditUser";
import Popup from "../../components/popup/Popup";

const useStyles = makeStyles((theme) => ({
  newButton: {
    position: "absolute",
    right: "10px",
  },
}));

const headCells = [
  { id: "username", label: "Username" },
  { id: "firstname", label: "Firstname" },
  { id: "lastname", label: "Lastname" },
  { id: "email", label: "Email" },
  { id: "phoneNumber", label: "Mobile" },
  { id: "address", label: "Address" },
  { id: "actions", label: "Actions", disableSorting: true },
];

function UserListTable(props) {
  const classes = useStyles();

  const [recordForEdit, setRecordForEdit] = useState(null);
  const [recordForDelete, setRecordForDelete] = useState(null);
  const [openPopup, setOpenPopup] = useState(false);

  const openInPopup = () => {
    console.log("Edittt");
    setOpenPopup(true);
  };

  const deleteUser = () => {
    console.log("Deletee");

    // console.log(recordForDelete);
    setOpenPopup(true);
  };

  const addBtn = () => {
    return (
      <Button
        variant="outlined"
        startIcon={<AddIcon />}
        className={classes.newButton}
        onClick={() => {
          setRecordForEdit(null);
          setOpenPopup(true);
        }}
      >
        Add New Admin
      </Button>
    );
  };
  return (
    <div>
      <DisplayList
        headCells={headCells}
        addBtn={props.type === "admin" ? addBtn() : ""}
        title={props.title}
        data={props.data}
      >
        {props.data.map((user) => {
          return (
            <React.Fragment>
              <TableRow key={user.id}>
                <TableCell>{user.username}</TableCell>
                <TableCell>{user.firstname}</TableCell>
                <TableCell>{user.lastname}</TableCell>
                <TableCell>{user.email}</TableCell>
                <TableCell>{user.phoneNumber}</TableCell>
                <TableCell>{user.address}</TableCell>

                <TableCell>
                  <IconButton
                    color="primary"
                    onClick={async () => {
                      await setRecordForEdit(user);
                      await openInPopup();
                    }}
                  >
                    <EditOutlinedIcon fontSize="small" />
                  </IconButton>
                  <IconButton
                    color="secondary"
                    onClick={async () => {
                      await setRecordForDelete(user);
                      await deleteUser();
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
      <Popup
        title={props.type}
        openPopup={openPopup}
        setOpenPopup={setOpenPopup}
      >
        <EditUser recordForEdit={recordForEdit} />
      </Popup>
    </div>
  );
}

export default UserListTable;
