import React, { useState, useEffect } from "react";
import SidebarOption from "./sidebarOption/SidebarOption";
import "./Sidebar.css";
import useStyles from "./styles";

import { useTheme } from "@material-ui/styles";
import { Drawer, Icon, IconButton, List } from "@material-ui/core";
import DashboardIcon from "@material-ui/icons/Dashboard";
import AddBoxIcon from "@material-ui/icons/AddBox";
import PeopleAltIcon from "@material-ui/icons/PeopleAlt";
import DriveEtaIcon from "@material-ui/icons/DriveEta";
import PersonIcon from "@material-ui/icons/Person";
import AirportShuttleIcon from "@material-ui/icons/AirportShuttle";
import SupervisorAccountIcon from "@material-ui/icons/SupervisorAccount";
import LocalAtmIcon from "@material-ui/icons/LocalAtm";
import DescriptionIcon from "@material-ui/icons/Description";
import PersonPinIcon from "@material-ui/icons/PersonPin";
import StarHalfIcon from "@material-ui/icons/StarHalf";
import LocalOfferIcon from "@material-ui/icons/LocalOffer";
import NotificationsActiveIcon from "@material-ui/icons/NotificationsActive";
import ArrowBack from "@material-ui/icons/ArrowBack";
import MenuIcon from "@material-ui/icons/Menu";

import classNames from "classnames";

const structure = [
  { id: 0, option: "Dashboard", icon: DashboardIcon, link: "/" },
  { id: 1, option: "Service Requests", icon: AddBoxIcon, link: "/st/navbar" },
  {
    id: 2,
    option: "Users",
    icon: PeopleAltIcon,
    children: [
      { option: "Customers", icon: PersonIcon, link: "/st/users/customers" },
      { option: "Admins", icon: PeopleAltIcon, link: "/st/users/admins" },
      {
        option: "Supervisors",
        icon: SupervisorAccountIcon,
        link: "/st/users/supervisors",
      },
      {
        option: "Mobile Mechanics",
        icon: PeopleAltIcon,
        link: "/st/users/mobile-mechanics",
      },
    ],
  },
  {
    id: 3,
    option: "Vehicles",
    icon: DriveEtaIcon,
    children: [
      {
        option: "Customer Vehicles",
        icon: DriveEtaIcon,
        link: "/st/vehicles/customer-vehicle",
      },
      {
        option: "Service Vehicles",
        icon: AirportShuttleIcon,
        link: "/st/vehicles/mobile-service-vehicle",
      },
      {
        option: "Vehicle Makes",
        icon: StarHalfIcon,
        link: "/st/vehicles/make",
      },
      {
        option: "Vehicle Models",
        icon: StarHalfIcon,
        link: "/st/vehicles/model",
      },
      {
        option: "Vehicle Engines",
        icon: StarHalfIcon,
        link: "/st/vehicles/engine",
      },
    ],
  },
  { id: 4, option: "Promotions", icon: LocalAtmIcon, link: "/st/promotions" },
  { id: 5, option: "Managing Documents", icon: DescriptionIcon },
  // { id: 5, option: "Managing Documents", icon: DescriptionIcon },

  // { id: 5, option: "Managing Documents", icon: DescriptionIcon },

  // { id: 5, option: "Managing Documents", icon: DescriptionIcon },
];

function Sidebar() {
  const classes = useStyles();
  const theme = useTheme();

  var [isSidebarOpened, setSidebarOpened] = useState(true);
  var [isPermanent, setPermanent] = useState(true);

  function toggleSidebar() {
    if (isSidebarOpened) setSidebarOpened(false);
    else setSidebarOpened(true);
  }

  return (
    <Drawer
      variant={isPermanent ? "permanent" : "temporary"}
      className={classNames(classes.drawer, {
        [classes.drawerOpen]: isSidebarOpened,
        [classes.drawerClose]: !isSidebarOpened,
      })}
      classes={{
        paper: classNames({
          [classes.drawerOpen]: isSidebarOpened,
          [classes.drawerClose]: !isSidebarOpened,
        }),
      }}
      open={isSidebarOpened}
    >
      <div className="sidebar">
        <IconButton onClick={() => toggleSidebar()}>
          {isSidebarOpened ? (
            <ArrowBack className="iconButton" />
          ) : (
            <MenuIcon className="iconButton" />
          )}
        </IconButton>
        <List>
          {structure.map((sidebarOption) => (
            <SidebarOption
              key={sidebarOption.icon}
              option={sidebarOption.option}
              Icon={sidebarOption.icon}
              children={sidebarOption.children}
              isSidebarOpened={isSidebarOpened}
              link={sidebarOption.link}
              {...sidebarOption}
            />
          ))}
        </List>
      </div>
    </Drawer>
  );
}

export default Sidebar;
