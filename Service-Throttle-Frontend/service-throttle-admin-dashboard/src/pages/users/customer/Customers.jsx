import React, { useState, useEffect } from "react";
import axios from "axios";
import { useDataLayerValue } from "../../../dataLayer/DataLayer";
import UserListTable from "../UserListTable";
import * as userService from "../../../services/userService";

function Customers() {
  const [type, setType] = useState("customer");
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
    const _customers = await userService.getUsers(authAxios, type);
    setCustomers(_customers);
  };

  return (
    <div>
      <UserListTable data={customers} title="Customer List"></UserListTable>
    </div>
  );
}

export default Customers;
