import React, { useState } from "react";
import { useFormik } from "formik";
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

const handleSubmit = (e) => {
  e.preventDefault();
  console.log("submitt");
};

function EditUser(initialFValues) {
  const [values, setValues] = useState(initialFValues.recordForEdit);

  const classes = useStyles();

  const formik = useFormik({
    initialValues: {
      username: "",
      password: "",
    },
    validationSchema: validationSchema,
    onSubmit: () => {},
  });

  const resetForm = () => {
    setValues(null);
  };

  console.log("Edorrr");
  // console.log(initialFValues.recordForEdit.username);

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
              value={values ? values.username : formik.values.username}
              onChange={formik.handleChange}
              error={formik.touched.username && Boolean(formik.errors.username)}
              helperText={formik.touched.username && formik.errors.username}
            ></TextField>
            <TextField
              type="text"
              id="firstname"
              name="firstname"
              label="Firstname"
              margin="dense"
              variant="outlined"
              className="input"
              value={values ? values.firstname : ""}
              // value={formik.values.username}
              // onChange={formik.handleChange}
              // error={
              //   formik.touched.username && Boolean(formik.errors.username)
              // }
              // helperText={formik.touched.username && formik.errors.username}
            ></TextField>
            <TextField
              type="text"
              id="username"
              name="username"
              label="Username"
              margin="dense"
              variant="outlined"
              className="input"
              // value={formik.values.username}
              // onChange={formik.handleChange}
              // error={
              //   formik.touched.username && Boolean(formik.errors.username)
              // }
              // helperText={formik.touched.username && formik.errors.username}
            ></TextField>
          </Grid>
          <Grid>
            <TextField
              type="text"
              id="username"
              name="username"
              label="Username"
              margin="dense"
              variant="outlined"
              className="input"
              // value={formik.values.username}
              // onChange={formik.handleChange}
              // error={
              //   formik.touched.username && Boolean(formik.errors.username)
              // }
              // helperText={formik.touched.username && formik.errors.username}
            ></TextField>
            <TextField
              type="text"
              id="username"
              name="username"
              label="Username"
              margin="dense"
              variant="outlined"
              className="input"
              // value={formik.values.username}
              // onChange={formik.handleChange}
              // error={
              //   formik.touched.username && Boolean(formik.errors.username)
              // }
              // helperText={formik.touched.username && formik.errors.username}
            ></TextField>
            <TextField
              type="text"
              id="username"
              name="username"
              label="Username"
              margin="dense"
              variant="outlined"
              className="input"
              // value={formik.values.username}
              // onChange={formik.handleChange}
              // error={
              //   formik.touched.username && Boolean(formik.errors.username)
              // }
              // helperText={formik.touched.username && formik.errors.username}
            ></TextField>
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
