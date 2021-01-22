import React, { useState, useEffect } from "react";
import axios from "axios";
import { useDataLayerValue } from "../../../dataLayer/DataLayer";
import Datalist from "../Userlist";

function Admins() {
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
    const res = await authAxios.get(`/users/admin`);

    const _admins = await res.data;
    setCustomers(_admins);
    console.log(admins);
  };

  return (
    <Datalist data={admins} authotity="admin" title="Admin List"></Datalist>
  );
}

export default Admins;
