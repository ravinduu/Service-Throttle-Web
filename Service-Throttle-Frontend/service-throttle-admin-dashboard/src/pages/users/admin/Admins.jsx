import React, { useState, useEffect } from "react";
import UserListTable from "../UserListTable";

function Admins() {
  const [type, setType] = useState("admin");

  return (
    <div>
      <UserListTable type={type}></UserListTable>
    </div>
  );
}

export default Admins;
