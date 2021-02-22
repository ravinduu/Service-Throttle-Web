import React, { useState, useEffect } from "react";
import UserListTable from "../UserListTable";

function Supervisors() {
  const [type, setType] = useState("supervisor");

  return <UserListTable type={type}></UserListTable>;
}

export default Supervisors;
