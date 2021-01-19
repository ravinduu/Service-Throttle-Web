import React, { useState } from "react";
import Table from "../../table/Table";

function Admins() {
  const [customers, setCustomers] = useState([]);

  return <Table data={customers} title="Admin List" />;
}

export default Admins;
