import React, { useState } from "react";
import "./SidebarOption.css";
import { Collapse, Divider, List, ListItem } from "@material-ui/core";
import { Link } from "react-router-dom";

function SidebarOption({ option, Icon, children, isSidebarOpened, link }) {
  var [isOpen, setIsOpen] = useState(false);

  function toggleCollapse(e) {
    if (isSidebarOpened) {
      e.preventDefault();
      setIsOpen(!isOpen);
    }
  }

  if (!children)
    return (
      <Link to={link} style={{ textDecoration: "none" }}>
        <ListItem button disableRipple>
          <div className="sidebarOption">
            {Icon && <Icon className="sidebarOption__icon" />}
            {Icon ? <p>{option}</p> : <h4>{option}</h4>}
          </div>
        </ListItem>
      </Link>
    );

  return (
    <div>
      <ListItem button onClick={toggleCollapse} disableRipple>
        <div
          className={
            isOpen && isSidebarOpened ? "sidebarOptionOpen" : "sidebarOption"
          }
        >
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
                  link={children.link}
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
