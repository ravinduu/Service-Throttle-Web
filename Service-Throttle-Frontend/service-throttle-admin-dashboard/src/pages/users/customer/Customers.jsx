import React, { useState, useEffect } from "react";
import UserListTable from "../UserListTable";

function Customers() {
  const [type, setType] = useState("customer");

  return (
    <div>
      <UserListTable type={type}></UserListTable>
    </div>
  );
}

export default Customers;
