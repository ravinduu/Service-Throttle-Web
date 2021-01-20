import React from "react";
import DataTable from "../table/DataTable";
import Title from "../title/Title";
import Button from "@material-ui/core/Button";
import CreateIcon from "@material-ui/icons/Create";
import DeleteIcon from "@material-ui/icons/Delete";
import Typography from "@material-ui/core/Typography";
import "./Userlist.css";

function Userlist(props) {
  return (
    <div className="userlistComponent">
      <Title className="title" title={props.title} variant="h3" />
      <DataTable data={props.data}></DataTable>
    </div>
  );
}

export default Userlist;
