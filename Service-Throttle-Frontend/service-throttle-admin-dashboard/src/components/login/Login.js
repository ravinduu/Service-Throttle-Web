import React, { useState } from "react";
import "./Login.css";
import axios from "axios";
import { useDataLayerValue } from "../../dataLayer/DataLayer";
import { Button, TextField, Typography, Container } from "@material-ui/core";

function Login() {
  const [credentials, setState] = useState({
    username: "",
    password: "",
  });

  const [{ token, api }, dispatch] = useDataLayerValue();

  const userLogin = async () => {
    try {
      const res = await axios.post(`${api}/login`, credentials);
      const _token = res.data.jwttoken;
      dispatch({
        type: "SET_TOKEN",
        token: _token,
      });
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <Container maxWidth="sm">
      <div className="base-container">
        <Typography variant="h4" gutterBottom>
          Service Throttle
        </Typography>
        <form>
          <div className="input">
            <div className="input-username">
              <TextField
                type="text"
                label="Username"
                margin="dense"
                variant="outlined"
                onChange={(option) => {
                  setState({ ...credentials, username: option.target.value });
                }}
              />
            </div>
            <div className="input-password">
              <TextField
                type="password"
                label="Password"
                margin="dense"
                variant="outlined"
                onChange={(option) => {
                  setState({ ...credentials, password: option.target.value });
                }}
              />
            </div>
          </div>
          <div className="btn-login">
            <Button
              variant="contained"
              color="primary"
              component="span"
              style={{
                backgroundColor: "#1167b1",
              }}
              onClick={() => {
                userLogin();
              }}
            >
              LOGIN
            </Button>
          </div>
        </form>
      </div>
    </Container>
  );
}

export default Login;
