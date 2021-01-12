import React from "react";
import { useDataLayerValue } from "../../dataLayer/DataLayer";

function Home() {
  const [{ user }, dispatch] = useDataLayerValue();

  console.log("in home" + user);
  return (
    <div>
      <h1>user</h1>
    </div>
  );
}

export default Home;
