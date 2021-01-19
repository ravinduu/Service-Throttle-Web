import React, { useState, useEffect } from "react";
import Table from "../../table/Table";
import axios from "axios";
import { useDataLayerValue } from "../../../dataLayer/DataLayer";

function Customers() {
  // const classes = useStyles();

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

  return <Table data={customers} title="Customer List" />;
}

export default Customers;
