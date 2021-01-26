import React, { useState } from "react";
import Title from "../../components/title/Title";
import MUIDataTable from "mui-datatables";
import Paper from "@material-ui/core/Paper";
import TableContainer from "@material-ui/core/TableContainer";
import PersonAddIcon from "@material-ui/icons/PersonAdd";
import { IconButton } from "@material-ui/core";
import DeleteIcon from "@material-ui/icons/Delete";
import Popup from "../../components/popup/Popup";
import Button from "@material-ui/core/Button";
import DialogActions from "@material-ui/core/DialogActions";

const columns = [
  { name: "id", lable: "id" },
  { name: "username", lable: "username" },
  { name: "firstname", lable: "firstname" },
  { name: "lastname", lable: "lastname" },
  { name: "email", lable: "email" },
  { name: "phoneNumber", lable: "phoneNumber" },
  { name: "address", lable: "address" },
];

function Userlist(props) {
  const [openPopup, setOpen] = useState(false);
  const [toDelete, setToDelete] = useState();

  const handleClose = () => {
    setOpen(false);
  };

  const handleRowClick = (rowData, rowMeta) => {
    // console.log(row);
    //implement edit function
  };

  const handleClickOpen = (rowsDeleted) => {
    // console.log(props.data[rowsDeleted.data[0].index]);
    setToDelete(props.data[rowsDeleted.data[0].index]);
    setOpen(true);
    console.log(toDelete);
  };

  const options = {
    filter: true,
    filterType: "dropdown",
    responsive: "stacked",
    onRowClick: handleRowClick,
    onRowsDelete: handleClickOpen,
  };

  return (
    <div className="userlistComponent">
      <div className="title">
        <Title title={props.title} variant="h2" />
      </div>
      <IconButton
        className="iconBtnAdd"
        onClick={() => console.log("add user")}
      >
        <PersonAddIcon />
      </IconButton>
      <TableContainer component={Paper} className="tableContainer">
        <div className="table">
          <MUIDataTable
            title={props.title}
            data={props.data}
            columns={columns}
            options={options}
          />
        </div>
      </TableContainer>
      <Popup
        title="Delete"
        text="Are you sure you want to delete this field??"
        openPopup={openPopup}
        setOpenPopup={setOpen}
      >
        <DialogActions>
          <Button onClick={handleClose} variant="outlined" color="primary">
            Cancel
          </Button>
          <Button
            onClick={handleClose}
            variant="contained"
            color="secondary"
            startIcon={<DeleteIcon />}
            autoFocus
          >
            Delete
          </Button>
        </DialogActions>
      </Popup>
    </div>
  );
}

export default Userlist;
