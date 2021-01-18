import React from "react";
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

const structure = [
  { id: 0, option: "Dashboard", icon: DashboardIcon, link: "/st" },
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
      },
      {
        option: "Service Vehicles",
        icon: AirportShuttleIcon,
      },
    ],
  },
  { id: 4, option: "Earning Reports", icon: LocalAtmIcon },
  { id: 5, option: "Managing Documents", icon: DescriptionIcon },
  // { id: 5, option: "Managing Documents", icon: DescriptionIcon },

  // { id: 5, option: "Managing Documents", icon: DescriptionIcon },

  // { id: 5, option: "Managing Documents", icon: DescriptionIcon },
];

function Sidebar() {
  var theme = useTheme();
  var classes = useStyles();

  var isSidebarOpened = "true";

  return (
    <Drawer variant="permanent" className={classes.drawer}>
      <div className="sidebar">
        <h1 className="search-bar">Search Bar</h1>
        <List>
          {structure.map((sidebarOption) => (
            <SidebarOption
              key={sidebarOption.icon}
              option={sidebarOption.option}
              Icon={sidebarOption.icon}
              children={sidebarOption.children}
              isSidebarOpened={true}
              link={sidebarOption.link}
              {...sidebarOption}
            />
          ))}
          {/* 
        <SidebarOption option="Earning Reports" Icon={LocalAtmIcon} />
        <SidebarOption option="Managing Documents" Icon={DescriptionIcon} />
        <SidebarOption option="God's View Locaton" Icon={PersonPinIcon} />
        <SidebarOption option="Reviews & Ratings" Icon={StarHalfIcon} />
        <SidebarOption option="Promo Code" Icon={LocalOfferIcon} />
        <SidebarOption
          option="Push Notifications"
          Icon={NotificationsActiveIcon}
        /> */}
        </List>
      </div>
    </Drawer>
  );
}

export default Sidebar;
