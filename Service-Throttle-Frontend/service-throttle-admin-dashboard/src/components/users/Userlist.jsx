import React, { useState } from "react";
import Title from "../title/Title";
import MUIDataTable from "mui-datatables";
import "./Userlist.css";
import Paper from "@material-ui/core/Paper";
import TableContainer from "@material-ui/core/TableContainer";
import PersonAddIcon from "@material-ui/icons/PersonAdd";
import { IconButton } from "@material-ui/core";

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
  const handleRowClick = (rowData, rowMeta) => {
    // console.log(row);
    //implement edit function
  };

  const deleteRowItem = (rowsDeleted, dataRows) => {
    console.log(props.data[rowsDeleted.data[0].index]);
  };

  const options = {
    filter: true,
    filterType: "dropdown",
    responsive: "stacked",
    onRowClick: handleRowClick,
    onRowsDelete: deleteRowItem,
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
    </div>
  );
}

export default Userlist;
