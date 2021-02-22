import React, { useState, useEffect } from "react";
import UserListTable from "../UserListTable";

function Mobilemechanics() {
  const [type, setType] = useState("mobile-mechanic");

  return <UserListTable type={type}></UserListTable>;
}

export default Mobilemechanics;
