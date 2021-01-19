import React from "react";
import useStyles from "./styles";
import { Typography } from "../wrappers/Wrappers";
import { useTheme } from "@material-ui/styles";

function Title(props) {
  const classes = useStyles();
  const theme = useTheme();

  return (
    <div>
      <Typography variant={props.variant} size="sm" className={classes.typo}>
        {props.title}
      </Typography>
    </div>
  );
}

export default Title;
