import { makeStyles } from "@material-ui/styles";

const drawerWidth = 300;

export default makeStyles((theme) => ({
  drawer: {
    width: drawerWidth,
    height: 1200,
    flexShrink: 0,
    whiteSpace: "nowrap",
    color: "#03254c",
  },
}));
