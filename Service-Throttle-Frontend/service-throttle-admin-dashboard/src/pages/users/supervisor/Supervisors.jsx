import React, { useState, useEffect } from "react";
import axios from "axios";
import { useDataLayerValue } from "../../../dataLayer/DataLayer";
import Datalist from "../Userlist";

function Supervisors() {
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
    const res = await authAxios.get(`/users/supervisor`);

    const _supervisors = await res.data;
    setCustomers(_supervisors);
    console.log(supervisors);
  };

  return (
    <Datalist
      data={supervisors}
      authotity="supervisor"
      title="Supervisor List"
    ></Datalist>
  );
}

export default Supervisors;
