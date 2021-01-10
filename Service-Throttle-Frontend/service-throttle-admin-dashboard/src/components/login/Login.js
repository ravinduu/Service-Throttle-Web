import React, { useState } from "react";
import "./Login.css";
import { userLogin } from "./userLogin";
import { Button, TextField, Typography, Container } from "@material-ui/core";

// const api = "http://localhost:8081/st";
// let users;
// let token = localStorage.getItem("token");
// let authAxios = axios.create({
//   baseURL: api,
//   headers: {
//     Authorization: `Bearer ${token}`,
//   },
// });

function Login() {
  const [credentials, setState] = useState({
    username: "",
    password: "",
  });

  // const userLogin = async () => {
  //   try {
  //     // const res = await axios.post(`${api}/login`, this.state);
  //     // token = res.data.jwttoken;
  //     // localStorage.setItem("token", token);
  //     console.log(credentials.username + "   " + credentials.password);
  //   } catch (err) {}
  //   console.log("err");
  // };

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
              onClick={() =>
                userLogin(credentials.username, credentials.password)
              }
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
