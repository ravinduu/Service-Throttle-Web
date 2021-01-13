import React from "react";
import SidebarOption from "./SidebarOption";
import "./Sidebar.css";
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

function Sidebar() {
  return (
    <div className="sidebar">
      <h1 className="search-bar">Search Bar</h1>
      <SidebarOption option="Dashboard" Icon={DashboardIcon} />
      <SidebarOption option="Service Requests" Icon={AddBoxIcon} />
      <SidebarOption option="Customers" Icon={PeopleAltIcon} />
      <SidebarOption option="Customer Vehicles" Icon={DriveEtaIcon} />
      <SidebarOption option="Mobile Mchanics" Icon={PersonIcon} />
      <SidebarOption
        option="Mobile Service Vehicles"
        Icon={AirportShuttleIcon}
      />
      <SidebarOption option="Supervisors" Icon={SupervisorAccountIcon} />
      <SidebarOption option="Earning Reports" Icon={LocalAtmIcon} />
      <SidebarOption option="Managing Documents" Icon={DescriptionIcon} />
      <SidebarOption option="God's View Locaton" Icon={PersonPinIcon} />
      <SidebarOption option="Reviews & Ratings" Icon={StarHalfIcon} />
      <SidebarOption option="Promo Code" Icon={LocalOfferIcon} />
      <SidebarOption
        option="Push Notifications"
        Icon={NotificationsActiveIcon}
      />
    </div>
  );
}

export default Sidebar;
