import React from "react";
import "./SidebarOption.css";

function SidebarOption({ option, Icon }) {
  return (
    <div className="sidebarOption">
      {Icon && <Icon className="sidebarOption__icon" />}
      {Icon ? <p>{option}</p> : <h4>{option}</h4>}
    </div>
  );
}

export default SidebarOption;
