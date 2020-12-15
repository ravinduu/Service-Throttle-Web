import React from "react";
import "./Login.css";
import { Button, TextField, Typography, Container } from "@material-ui/core";

function login() {
  return (
    <div className="login">
      <Typography className="login__text" variant="h4" gutterBottom>
        Service Throttle
      </Typography>
      <Button className="login__buttons" variant="contained" color="primary">
        Login With GOOGLE
      </Button>
      <br></br>
      <Button className="login__buttons" variant="contained" color="primary">
        Login With Facebook
      </Button>

      <hr />
      <TextField
        className="login__inputs"
        type="text"
        label="Username"
        margin="dense"
        variant="outlined"
      />
      <TextField
        className="login__inputs"
        type="text"
        label="Password"
        margin="dense"
        variant="outlined"
        type="password"
      />
    </div>
  );
}

export default login;
