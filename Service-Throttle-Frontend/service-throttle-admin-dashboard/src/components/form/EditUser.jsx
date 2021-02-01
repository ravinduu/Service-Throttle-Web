import React, { useState } from "react";
import { useFormik, Form } from "formik";
import * as yup from "yup";
import { Grid, Button, TextField } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import "./EditUser.css";

const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(1),
    },
  },
}));

function EditUser(data) {
  const classes = useStyles();

  const [recordForEdit, setValues] = useState(data.recordForEdit);

  const isAddMode = !recordForEdit;

  const _initialValues = {
    username: "",
    firstname: "",
    lastname: "",
    phoneNumber: "",
    email: "",
    address: "",
  };

  const validationSchema = yup.object({
    username: yup.string().required("Username is required"),
    firstname: yup.string().required("Firstname is required"),
    lastname: yup.string().required("Lastname is required"),
    email: yup.string().email("Email is invalid").required("Email is required"),
    phoneNumber: yup.string().required("Phonenumber is required"),
    address: yup.string().required("Address is required"),
  });

  function onSubmit(user) {
    if (isAddMode) {
      // createUser(fields, setSubmitting);
      console.log("Add ");
      console.log(user);
    } else {
      // updateUser(id, fields, setSubmitting);
      console.log("Edit " + user);
    }
  }

  function createUser(fields, setSubmitting) {}

  function updateUser(id, fields, setSubmitting) {}

  const formik = useFormik({
    initialValues: _initialValues,
    validationSchema: validationSchema,
    onSubmit: (values) => {
      onSubmit(values);
    },
  });

  const resetForm = () => {
    formik.handleReset();
  };

  return (
    <div>
      <form onSubmit={formik.handleSubmit}>
        <div className="editUserForm">
          <Grid>
            <TextField
              type="text"
              id="username"
              name="username"
              label="Username"
              margin="dense"
              variant="outlined"
              className="input"
              value={
                recordForEdit ? recordForEdit.username : formik.values.username
              }
              onChange={formik.handleChange}
              error={formik.touched.username && Boolean(formik.errors.username)}
              helperText={formik.touched.username && formik.errors.username}
            />
            <TextField
              type="text"
              id="firstname"
              name="firstname"
              label="Firstname"
              margin="dense"
              variant="outlined"
              className="input"
              value={
                recordForEdit
                  ? recordForEdit.firstname
                  : formik.values.firstname
              }
              onChange={formik.handleChange}
              error={
                formik.touched.firstname && Boolean(formik.errors.firstname)
              }
              helperText={formik.touched.firstname && formik.errors.firstname}
            />
            <TextField
              type="text"
              id="lastname"
              name="lastname"
              label="lastname"
              margin="dense"
              variant="outlined"
              className="input"
              value={
                recordForEdit ? recordForEdit.lastname : formik.values.lastname
              }
              onChange={formik.handleChange}
              error={formik.touched.lastname && Boolean(formik.errors.lastname)}
              helperText={formik.touched.lastname && formik.errors.lastname}
            />
          </Grid>
          <Grid>
            <TextField
              type="text"
              id="email"
              name="email"
              label="email"
              margin="dense"
              variant="outlined"
              className="input"
              value={recordForEdit ? recordForEdit.email : formik.values.email}
              onChange={formik.handleChange}
              error={formik.touched.email && Boolean(formik.errors.email)}
              helperText={formik.touched.email && formik.errors.email}
            />
            <TextField
              type="text"
              id="phoneNumber"
              name="phoneNumber"
              label="phoneNumber"
              margin="dense"
              variant="outlined"
              className="input"
              value={
                recordForEdit
                  ? recordForEdit.phoneNumber
                  : formik.values.phoneNumber
              }
              onChange={formik.handleChange}
              error={
                formik.touched.phoneNumber && Boolean(formik.errors.phoneNumber)
              }
              helperText={
                formik.touched.phoneNumber && formik.errors.phoneNumber
              }
            />{" "}
            <TextField
              type="text"
              id="address"
              name="address"
              label="address"
              margin="dense"
              variant="outlined"
              className="input"
              value={
                recordForEdit ? recordForEdit.address : formik.values.address
              }
              onChange={formik.handleChange}
              error={formik.touched.address && Boolean(formik.errors.address)}
              helperText={formik.touched.address && formik.errors.address}
            />
          </Grid>
        </div>
        <div className="actions">
          <div className={classes.root}>
            <Button variant="outlined" type="submit" value="Submit">
              Submit
            </Button>
            <Button
              variant="outlined"
              color="primary"
              value="Reset"
              color="default"
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

export default EditUser;
