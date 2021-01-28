import React, { useState, useEffect } from "react";
import axios from "axios";
import { useDataLayerValue } from "../../../dataLayer/DataLayer";
import UserListTable from "../UserListTable";
import * as userService from "../../../services/userService";

function Admins() {
  const [type, setType] = useState("admin");

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
      fetchAdmins();
    } else console.log("no token");
  }, []);

  const [admins, setCustomers] = useState([]);

  const fetchAdmins = async () => {
    const _admins = await userService.getUsers(authAxios, type);
    setCustomers(_admins);
  };

  return (
    <div>
      <UserListTable
        data={admins}
        title="Admin List"
        type={type}
      ></UserListTable>
    </div>
  );
}

export default Admins;
