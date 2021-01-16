import React from "react";
import "./Login.css";
import axios from "axios";
import { useDataLayerValue } from "../../dataLayer/DataLayer";
import { Button, TextField, Typography, Container } from "@material-ui/core";
import { useFormik } from "formik";
import * as yup from "yup";

const validationSchema = yup.object({
  username: yup
    .string("Enter your username")
    .min(5, "Username should be of minimum 5 characters length")
    .required("Username is required"),
  password: yup
    .string("Enter your password")
    .min(5, "Password should be of minimum 8 characters length")
    .required("Password is required"),
});

function Login() {
  // const [credentials, setState] = useState({
  //   username: "",
  //   password: "",
  // });
  const formik = useFormik({
    initialValues: {
      username: "",
      password: "",
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      userLogin(values);
    },
  });

  const [{ token, api }, dispatch] = useDataLayerValue();

  const userLogin = async (credentials) => {
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
        <form onSubmit={formik.handleSubmit}>
          <div className="input">
            <div className="input-username">
              <TextField
                type="text"
                id="username"
                name="username"
                label="Username"
                margin="dense"
                variant="outlined"
                value={formik.values.username}
                onChange={formik.handleChange}
                error={
                  formik.touched.username && Boolean(formik.errors.username)
                }
                helperText={formik.touched.username && formik.errors.username}
              />
            </div>
            <div className="input-password">
              <TextField
                type="password"
                id="password"
                name="password"
                label="Password"
                margin="dense"
                variant="outlined"
                value={formik.values.password}
                onChange={formik.handleChange}
                error={
                  formik.touched.password && Boolean(formik.errors.password)
                }
                helperText={formik.touched.password && formik.errors.password}
              />
            </div>
          </div>
          <div className="btn-login">
            <Button
              variant="contained"
              color="primary"
              type="submit"
              style={{
                backgroundColor: "#1167b1",
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
