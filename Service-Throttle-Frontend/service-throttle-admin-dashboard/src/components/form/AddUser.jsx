import React, { useState } from "react";
import { useFormik } from "formik";
import * as yup from "yup";
import { makeStyles } from "@material-ui/core/styles";
import { Button, TextField } from "@material-ui/core";
import "./AddUser.css";

const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(1),
    },
  },
}));

function AddUser(props) {
  const { addNewUser } = props;

  const classes = useStyles();
  const [user, setUser] = useState({
    username: "",
    email: "",
    password: "",
  });
  const initialValues = {
    username: "",
    email: "",
    password: "",
    confirmPassword: "",
  };

  const validationSchema = yup.object({
    username: yup
      .string("Enter your username")
      .min(5, "Username should be of minimum 5 characters length")
      .required("Username is required"),
    email: yup.string().email("Email is invalid").required("Email is required"),
    password: yup
      .string("Enter your password")
      .min(8, "Password should be of minimum 8 characters length")
      .required("Password is required"),
    confirmPassword: yup
      .string()
      .oneOf([yup.ref("password"), null], "Passwords must match")
      .required("Confirm Password is required"),
  });

  const formik = useFormik({
    initialValues: initialValues,
    validationSchema: validationSchema,

    onSubmit: (values) => {
      user.username = values.username;
      user.password = values.password;
      user.email = values.email;
      addUser(user);
    },
  });

  const addUser = async (user) => {
    addNewUser(user);
    // console.log(user);
  };

  const resetForm = () => {
    formik.handleReset();
  };

  return (
    <div>
      <form onSubmit={formik.handleSubmit}>
        <div className={classes.root}>
          <TextField
            type="text"
            id="username"
            name="username"
            label="Username"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.username}
            onChange={formik.handleChange}
            error={formik.touched.username && Boolean(formik.errors.username)}
            helperText={formik.touched.username && formik.errors.username}
          />
          <TextField
            type="text"
            id="email"
            name="email"
            label="Email"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.email}
            onChange={formik.handleChange}
            error={formik.touched.email && Boolean(formik.errors.email)}
            helperText={formik.touched.email && formik.errors.email}
          />{" "}
          <TextField
            type="password"
            id="password"
            name="password"
            label="Password"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.password}
            onChange={formik.handleChange}
            error={formik.touched.password && Boolean(formik.errors.password)}
            helperText={formik.touched.password && formik.errors.password}
          />
          <TextField
            type="password"
            id="confirmPassword"
            name="confirmPassword"
            label="Confirm Password"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.confirmPassword}
            onChange={formik.handleChange}
            error={
              formik.touched.confirmPassword &&
              Boolean(formik.errors.confirmPassword)
            }
            helperText={
              formik.touched.confirmPassword && formik.errors.confirmPassword
            }
          />
        </div>
        <div className="actions">
          <div className={classes.root}>
            <Button
              variant="outlined"
              type="submit"
              value="Submit"
              color="primary"
            >
              Add
            </Button>
            <Button
              variant="outlined"
              color="primary"
              value="Reset"
              color="secondary"
              onClick={resetForm}
            >
              Reset
            </Button>
          </div>
        </div>
      </form>
    </div>
  );
}

export default AddUser;
