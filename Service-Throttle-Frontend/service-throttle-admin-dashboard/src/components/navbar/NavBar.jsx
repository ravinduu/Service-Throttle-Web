import React from "react";
import { useDataLayerValue } from "../../dataLayer/DataLayer";

function NavBar() {
  const [{ user }] = useDataLayerValue();

  return <div>{user ? <h1> {user.email} </h1> : <h1>Navbar</h1>}</div>;
}

export default NavBar;
