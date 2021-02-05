import React, { useState, useEffect } from "react";
import DisplayList from "../../components/table/DisplayList";
import { useDataLayerValue } from "../../dataLayer/DataLayer";
import * as userService from "../../services/userService";

import axios from "axios";

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
import AddUser from "../../components/form/AddUser";
import Delete from "../../components/deleteItem/Delete";
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
  const [{ token, api }] = useDataLayerValue();
  const [users, setCustomers] = useState([]);
  const classes = useStyles();
  const [recordForEdit, setRecordForEdit] = useState(null);
  const [recordForDelete, setRecordForDelete] = useState(null);
  const [openPopupEdit, setOpenPopupEdit] = useState(false);
  const [openPopupDelete, setOpenPopupDelete] = useState(false);
  const [openPopupAdd, setOpenPopupAdd] = useState(false);
  const [timesReload, setTimesReload] = useState(0);

  let authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  useEffect(() => {
    if (token) {
      fetchusers();
    } else console.log("no token");
  }, [timesReload]);

  const fetchusers = async () => {
    const _users = await userService.getUsers(authAxios, props.type);
    setCustomers(_users);
  };

  const deleteUser = (user) => {
    userService.deleteUser(authAxios, user.username).then(() => {
      setOpenPopupDelete(false);
      setTimesReload(timesReload + 1);
    });
  };

  const editUser = (user) => {
    userService.editUser(authAxios, user).then(() => {
      setOpenPopupEdit(false);
      setTimesReload(timesReload + 1);
    });
  };

  const addBtn = () => {
    return (
      <Button
        variant="outlined"
        startIcon={<AddIcon />}
        className={classes.newButton}
        onClick={() => {
          setOpenPopupAdd(true);
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
        title={props.type + " list"}
        data={users}
      >
        {users.map((user) => {
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
                      setOpenPopupEdit(true);
                    }}
                  >
                    <EditOutlinedIcon fontSize="small" />
                  </IconButton>
                  <IconButton
                    color="secondary"
                    onClick={async () => {
                      await setRecordForDelete(user);
                      setOpenPopupDelete(true);
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
        openPopup={openPopupEdit}
        setOpenPopup={setOpenPopupEdit}
      >
        <EditUser editUser={editUser} recordForEdit={recordForEdit} />
      </Popup>

      <Popup
        title="Delete"
        openPopup={openPopupDelete}
        setOpenPopup={setOpenPopupDelete}
      >
        <Delete
          deleteRecord={deleteUser}
          recordForDelete={recordForDelete}
          setOpenPopupDelete={setOpenPopupDelete}
          name={recordForDelete ? recordForDelete.username : ""}
        />
      </Popup>

      <Popup
        title="Register New Admin"
        openPopup={openPopupAdd}
        setOpenPopup={setOpenPopupAdd}
      >
        <AddUser />
      </Popup>
    </div>
  );
}

export default UserListTable;
