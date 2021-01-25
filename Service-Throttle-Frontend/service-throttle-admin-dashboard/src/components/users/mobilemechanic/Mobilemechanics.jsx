import React, { useState, useEffect } from "react";
import axios from "axios";
import { useDataLayerValue } from "../../../dataLayer/DataLayer";
import Datalist from "../Userlist";

function Mobilemechanics() {
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
      fetchMM();
    } else console.log("no token");
  }, []);

  const [mobileMechanic, setCustomers] = useState([]);

  const fetchMM = async () => {
    const res = await authAxios.get(`/users/Mobile Mechanic`);

    const _mobileMechanic = await res.data;
    setCustomers(_mobileMechanic);
    console.log(mobileMechanic);
  };

  return (
    <Datalist
      data={mobileMechanic}
      authotity="mobile_mechanic"
      title="Mobile Mechanic List"
    ></Datalist>
  );
}

export default Mobilemechanics;
