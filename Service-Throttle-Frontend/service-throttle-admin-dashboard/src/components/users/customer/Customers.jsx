import React, { useState, useEffect } from "react";
import axios from "axios";
import { useDataLayerValue } from "../../../dataLayer/DataLayer";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";

const useStyles = makeStyles({
  table: {
    minWidth: 650,
  },
});

function Customers() {
  const classes = useStyles();

  const [{ token, api }] = useDataLayerValue();

  let authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "text/plain",
    },
  });

  useEffect(() => {
    if (token) {
      fetchCustomers();
    } else console.log("no token");
  }, []);

  const [customers, setCustomers] = useState([]);

  const fetchCustomers = async () => {
    const res = await authAxios.get(`/users/customer`);

    const _customers = await res.data;
    setCustomers(_customers);
    console.log(customers);
  };

  return (
    <div>
      <TableContainer component={Paper}>
        <Table className={classes.table} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell align="left">Id</TableCell>
              <TableCell align="left">Username</TableCell>
              <TableCell align="left">Firstname</TableCell>
              <TableCell align="left">Lastname</TableCell>
              <TableCell align="left">Email</TableCell>
              <TableCell align="left">Phonenumber</TableCell>
              <TableCell align="left">Address</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {customers.map((customer) => (
              <TableRow key={customer.id}>
                <TableCell component="th" scope="row">
                  {customer.id}
                </TableCell>
                <TableCell align="left">{customer.username}</TableCell>
                <TableCell align="left">{customer.firstname}</TableCell>
                <TableCell align="left">{customer.lastname}</TableCell>
                <TableCell align="left">{customer.email}</TableCell>
                <TableCell align="left">{customer.phoneNumber}</TableCell>
                <TableCell align="left">{customer.address}</TableCell>
                {/* <TableCell align="left">{customer.username}</TableCell> */}

                {/* customer ? <h1>{customer.username}</h1> : <h1>customers</h1> */}
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}

export default Customers;
