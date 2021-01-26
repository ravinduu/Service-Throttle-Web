import React, { useState, useEffect } from "react";
import axios from "axios";
import { useDataLayerValue } from "../../../dataLayer/DataLayer";
import UserListTable from "../UserListTable";
import * as userService from "../../../services/userService";

function Supervisors() {
  const [type, setType] = useState("supervisor");

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
      fetchSupervisors();
    } else console.log("no token");
  }, []);

  const [supervisors, setCustomers] = useState([]);

  const fetchSupervisors = async () => {
    const _supervisors = await userService.getUsers(authAxios, type);
    setCustomers(_supervisors);
    console.log(supervisors);
  };

  return (
    <UserListTable
      data={supervisors}
      title="Supervisors List"
      type={type}
    ></UserListTable>
  );
}

export default Supervisors;
