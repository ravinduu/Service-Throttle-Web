import React from "react";
import MUIDataTable from "mui-datatables";
import "./Table.css";
import TableContainer from "@material-ui/core/TableContainer";
import Paper from "@material-ui/core/Paper";
import Title from "../title/Title";

const columns = [
  { name: "id", lable: "id" },
  { name: "username", lable: "username" },
  { name: "firstname", lable: "firstname" },
  { name: "lastname", lable: "lastname" },
  { name: "email", lable: "email" },
  { name: "phoneNumber", lable: "phoneNumber" },
  { name: "address", lable: "address" },
  // { name: "created", lable: "created" },
];

const options = {
  filterType: "checkbox",
};
function Table(props) {
  return (
    <div className="container">
      <TableContainer component={Paper} className="tableContainer">
        <div className="tabletitle">
          <Title title={props.title} variant="h3" />
        </div>
        <MUIDataTable
          className="table"
          title={"Customer List"}
          data={props.data}
          columns={columns}
          options={options}
        />
      </TableContainer>
    </div>
  );
}

export default Table;
