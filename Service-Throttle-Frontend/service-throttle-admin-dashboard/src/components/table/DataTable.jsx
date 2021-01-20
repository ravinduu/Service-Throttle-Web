import React, { Component } from "react";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import CreateIcon from "@material-ui/icons/Create";
import DeleteIcon from "@material-ui/icons/Delete";
import PersonAddIcon from "@material-ui/icons/PersonAdd";
import Typography from "@material-ui/core/Typography";
import { Tab, IconButton } from "@material-ui/core";
import "./DataTable.css";

function DataTable(props) {
  return (
    <div className="tableContainer">
      <Table className="table" table-layout="fixed" aria-label="simple table">
        <TableHead>
          <IconButton
            className="iconBtnAdd"
            onClick={() => console.log("add user")}
          >
            <PersonAddIcon />
          </IconButton>
          <TableRow>
            <TableCell white-space="nowrap" align="left">
              Id
            </TableCell>
            <TableCell white-space="nowrap" align="left">
              Username
            </TableCell>
            <TableCell white-space="nowrap" align="left">
              Firstname
            </TableCell>
            <TableCell white-space="nowrap" align="left">
              Lastname
            </TableCell>
            <TableCell white-space="nowrap" align="left">
              Email
            </TableCell>
            <TableCell white-space="nowrap" align="left">
              Phonenumber
            </TableCell>
            <TableCell white-space="nowrap" align="left">
              Address
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {props.data.map((customer) => (
            <TableRow key={customer.id}>
              <TableCell component="th" scope="row">
                {customer.id}
              </TableCell>
              <TableCell white-space="nowrap" align="left">
                {customer.username}
              </TableCell>
              <TableCell white-space="nowrap" align="left">
                {customer.firstname}
              </TableCell>
              <TableCell white-space="nowrap" align="left">
                {customer.lastname}
              </TableCell>
              <TableCell white-space="nowrap" align="left">
                {customer.email}
              </TableCell>
              <TableCell white-space="nowrap" align="left">
                {customer.phoneNumber}
              </TableCell>
              <TableCell white-space="nowrap" align="left">
                {customer.address}
              </TableCell>
              {/* <div className="actions">
                <IconButton onClick={() => console.log("edit " + customer.id)}>
                  <CreateIcon />
                </IconButton>
                <IconButton
                  onClick={() => console.log("delete " + customer.id)}
                >
                  <DeleteIcon />
                </IconButton>
              </div> */}
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
}

export default DataTable;
