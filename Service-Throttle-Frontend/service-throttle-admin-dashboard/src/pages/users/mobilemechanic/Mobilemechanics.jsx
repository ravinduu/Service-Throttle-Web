import React, { useState, useEffect } from "react";
import axios from "axios";
import { useDataLayerValue } from "../../../dataLayer/DataLayer";
import UserListTable from "../UserListTable";
import * as userService from "../../../services/userService";

function Mobilemechanics() {
  const [type, setType] = useState("mobile-mechanic");

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

  const [mobileMechanic, setMobileMechanic] = useState([]);

  const fetchMM = async () => {
    const _mobileMechanic = await userService.getUsers(authAxios, type);
    setMobileMechanic(_mobileMechanic);
    console.log(mobileMechanic);
  };

  return (
    <UserListTable
      data={mobileMechanic}
      title="Mobile Mechanic List"
    ></UserListTable>
  );
}

export default Mobilemechanics;
