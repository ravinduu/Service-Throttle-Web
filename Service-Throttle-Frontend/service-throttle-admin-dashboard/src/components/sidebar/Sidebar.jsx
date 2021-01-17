import React from "react";
import SidebarOption from "./sidebarOption/SidebarOption";
import "./Sidebar.css";
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
import useStyles from "./styles";
import { useTheme } from "@material-ui/styles";

const structure = [
  { id: 0, option: "Dashboard", icon: DashboardIcon },
  { id: 1, option: "Service Requests", icon: AddBoxIcon },
  {
    id: 2,
    option: "Users",
    icon: PeopleAltIcon,
    children: [
      { option: "Customers", link: "/app/ui/icons", icon: PersonIcon },
      { option: "Admins", link: "/app/ui/charts", icon: PeopleAltIcon },
      {
        option: "Supervisors",
        link: "/app/ui/charts",
        icon: SupervisorAccountIcon,
      },
      {
        option: "Mobile Mechanics",
        link: "/app/ui/charts",
        icon: PeopleAltIcon,
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
        link: "/app/ui/icons",
        icon: DriveEtaIcon,
      },
      {
        option: "Service Vehicles",
        link: "/app/ui/charts",
        icon: AirportShuttleIcon,
      },
    ],
  },
  { id: 4, option: "Earning Reports", icon: LocalAtmIcon },
  { id: 5, option: "Managing Documents", icon: DescriptionIcon },
  { id: 5, option: "Managing Documents", icon: DescriptionIcon },

  { id: 5, option: "Managing Documents", icon: DescriptionIcon },

  { id: 5, option: "Managing Documents", icon: DescriptionIcon },
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
