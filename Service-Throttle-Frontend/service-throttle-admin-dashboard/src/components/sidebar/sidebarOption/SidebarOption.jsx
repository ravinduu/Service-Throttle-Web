import React, { useState } from "react";
import "./SidebarOption.css";
import { Collapse, Divider, List, ListItem } from "@material-ui/core";

function SidebarOption({ option, Icon, children, isSidebarOpened }) {
  var [isOpen, setIsOpen] = useState(false);

  function toggleCollapse(e) {
    if (isSidebarOpened) {
      e.preventDefault();
      setIsOpen(!isOpen);
    }
  }

  if (!children)
    return (
      <ListItem button disableRipple>
        <div className="sidebarOption">
          {Icon && <Icon className="sidebarOption__icon" />}
          {Icon ? <p>{option}</p> : <h4>{option}</h4>}
        </div>
      </ListItem>
    );

  return (
    <div>
      <ListItem button onClick={toggleCollapse} disableRipple>
        <div className={isOpen ? "sidebarOptionOpen" : "sidebarOption"}>
          {Icon && <Icon className="sidebarOption__icon" />}
          {Icon ? <p>{option}</p> : <h4>{option}</h4>}
        </div>
      </ListItem>
      {children && (
        <Collapse in={isOpen && isSidebarOpened} timeout="auto" unmountOnExit>
          <div className="childrenLink">
            <List component="div" disablePadding>
              {children.map((childrenLink) => (
                <SidebarOption
                  option={childrenLink.option}
                  Icon={childrenLink.icon}
                  nested
                  {...childrenLink}
                />
              ))}
            </List>
          </div>
        </Collapse>
      )}
    </div>
  );
}

export default SidebarOption;
