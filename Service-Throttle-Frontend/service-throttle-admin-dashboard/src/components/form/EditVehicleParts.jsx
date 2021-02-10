import React from "react";
import { useFormik } from "formik";
import * as yup from "yup";
import { makeStyles } from "@material-ui/core/styles";
import { Button, TextField } from "@material-ui/core";

import "./EditUser.css";

const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(1),
    },
  },
}));

function EditVehicleParts(props) {
  const { editVehiclePart, recordForEdit, partType } = props;
  const isForAdd = !editVehiclePart.id;
  const classes = useStyles();
  let validationSchema;

  if (partType === "engine") {
    validationSchema = yup.object({
      engine: yup.string().required("Engine is required"),
    });
  } else if (partType === "make") {
    validationSchema = yup.object({
      make: yup.string().required("Make is required"),
    });
  } else {
    validationSchema = yup.object({
      model: yup.string().required("Model is required"),
    });
  }

  const formik = useFormik({
    initialValues: recordForEdit,
    validationSchema: validationSchema,
    onSubmit: (values) => {
      editVehiclePart(values);
    },
  });

  const resetForm = () => {
    formik.handleReset();
  };

  if (partType === "engine") {
    return (
      <div>
        <form onSubmit={formik.handleSubmit}>
          <TextField
            type="text"
            id="engine"
            name="engine"
            label="Engine"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.engine}
            onChange={formik.handleChange}
            error={formik.touched.engine && Boolean(formik.errors.engine)}
            helperText={formik.touched.engine && formik.errors.engine}
          />
          <div className={classes.root}>
            <Button
              variant="outlined"
              type="submit"
              value="Submit"
              color="primary"
            >
              Submit
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
        </form>
      </div>
    );
  }

  if (partType === "make") {
    return (
      <div>
        <form onSubmit={formik.handleSubmit}>
          <TextField
            type="text"
            id="make"
            name="make"
            label="Make"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.make}
            onChange={formik.handleChange}
            error={formik.touched.make && Boolean(formik.errors.make)}
            helperText={formik.touched.make && formik.errors.make}
          />
          <div className={classes.root}>
            <Button
              variant="outlined"
              type="submit"
              value="Submit"
              color="primary"
            >
              Submit
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
        </form>
      </div>
    );
  }

  return (
    <div>
      <form onSubmit={formik.handleSubmit}>
        <h7>{formik.values.vehicleMake.make}</h7>
        <br></br>
        <TextField
          type="text"
          id="model"
          name="model"
          label="Model"
          margin="dense"
          variant="outlined"
          className="input"
          value={formik.values.model}
          onChange={formik.handleChange}
          error={formik.touched.model && Boolean(formik.errors.model)}
          helperText={formik.touched.model && formik.errors.model}
        />
        <div className={classes.root}>
          <Button
            variant="outlined"
            type="submit"
            value="Submit"
            color="primary"
          >
            Submit
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
      </form>
    </div>
  );
}

export default EditVehicleParts;
