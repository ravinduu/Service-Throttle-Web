import React from "react";
import { useDataLayerValue } from "../../dataLayer/DataLayer";
import Title from "../title/Title";

function NavBar() {
  const [{ user }] = useDataLayerValue();

  return (
    <div>
      {user ? (
        <h1> {user.email} </h1>
      ) : (
        <div>
          <Title title="Navbar" variant="h1" />
        </div>
      )}
    </div>
  );
}

export default NavBar;
