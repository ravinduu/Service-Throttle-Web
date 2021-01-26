import React, { useState } from "react";
import DisplayList from "../../components/table/DisplayList";
import { TableRow, TableCell, IconButton } from "@material-ui/core";
import CloseIcon from "@material-ui/icons/Close";
import EditOutlinedIcon from "@material-ui/icons/EditOutlined";

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
  const [recordForEdit, setRecordForEdit] = useState(null);

  const [openPopup, setOpenPopup] = useState(false);

  const openInPopup = (user) => {
    setRecordForEdit(user);
    setOpenPopup(true);
  };
  return (
    <div>
      <DisplayList headCells={headCells} title={props.title} data={props.data}>
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
                    onClick={() => {
                      openInPopup(user);
                      console.log(user);
                    }}
                  >
                    <EditOutlinedIcon fontSize="small" />
                  </IconButton>
                  <IconButton
                    color="secondary"
                    onClick={() => {
                      openInPopup(user);
                      console.log("Delete");
                      console.log(user);
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

export default UserListTable;
