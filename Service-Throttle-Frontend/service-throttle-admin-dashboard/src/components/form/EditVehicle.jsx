import React from "react";
import { useFormik } from "formik";
import * as yup from "yup";
import { Grid, Button, TextField } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import { Select, InputLabel, MenuItem, FormControl } from "@material-ui/core";
import { useDataLayerValue } from "../../dataLayer/DataLayer";

const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(1),
    },
  },
  inputLable: {
    paddingLeft: 15,
  },
}));

function EditVehicle(props) {
  const [{ makes }] = useDataLayerValue();

  const classes = useStyles();

  const { editVehicle, recordForEdit } = props;

  const isAdd = !recordForEdit.id;

  const validationSchema = yup.object({
    mobileMechanic: yup.object(),
    vehicleMake: yup.object().required("Make is required"),
    vehicleModel: yup.object.required("Model is required"),
    vehicleModel: yup.object.required("Model is required"),
    capacity: yup.number().positive().required("Capacity is required"),
    year: yup.number().positive().required("Year is required"),
  });

  function onSubmit(Vehicle) {
    updateVehicle(Vehicle);
  }

  const updateVehicle = async (VehicleForEdit) => {
    editVehicle(VehicleForEdit);
  };

  const resetForm = () => {
    formik.handleReset();
  };

  const formik = useFormik({
    initialValues: recordForEdit,
    validationSchema: validationSchema,
    onSubmit: (values) => {
      onSubmit(values);
    },
  });

  return (
    <div>
      <form onSubmit={formik.handleSubmit}>
        <div className="editUserForm">
          <Grid></Grid>
          <Grid>
            <TextField
              type="text"
              id="phoneNumber"
              name="phoneNumber"
              label="phoneNumber"
              margin="dense"
              variant="outlined"
              className="input"
              value={formik.values.phoneNumber}
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
              value={formik.values.address}
              onChange={formik.handleChange}
              error={formik.touched.address && Boolean(formik.errors.address)}
              helperText={formik.touched.address && formik.errors.address}
            />
          </Grid>
        </div>
        <div className="actions">
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
        </div>
      </form>
    </div>
  );
}

export default EditVehicle;
