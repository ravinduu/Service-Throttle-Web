import React from "react";
import { useDataLayerValue } from "../../dataLayer/DataLayer";

function Footer() {
  const [{ user }, dispatch] = useDataLayerValue();

  return (
    <div>
      <h1>{user ? user.username : ""}</h1>
    </div>
  );
}

export default Footer;
