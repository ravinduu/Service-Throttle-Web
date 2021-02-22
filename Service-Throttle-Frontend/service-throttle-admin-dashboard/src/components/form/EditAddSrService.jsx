import React from "react";
import { useFormik } from "formik";
import * as yup from "yup";
import { makeStyles } from "@material-ui/core/styles";
import { Button, TextField } from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(1),
    },
  },
}));

function EditAddSrService(props) {
  const { editSrService, recordForEdit } = props;
  const classes = useStyles();

  const validationSchema = yup.object({
    vehicleServiceName: yup
      .string("Enter new name")
      .required("vehicle Service Name is required"),
    vehicleServicePrice: yup
      .number()
      .min(10, "Add value greater than 10")
      .required("Price is required"),
    vehicleServiceDescription: yup
      .string("Enter new Desctiption")
      .required("Description is required"),
    vehicleServiceType: yup
      .string("Enter new Vehicle Service Type")
      .required("Type is required"),
    imageURL: yup.string("Enter new Image url"),
  });

  const formik = useFormik({
    initialValues: recordForEdit,
    validationSchema: validationSchema,
    onSubmit: (values) => {
      console.log(values);
      editSrService(values);
    },
  });

  const resetForm = () => {
    formik.handleReset();
  };

  return (
    <div>
      <form onSubmit={formik.handleSubmit}>
        <div className={classes.root}>
          <TextField
            type="text"
            id="vehicleServiceName"
            name="vehicleServiceName"
            label="Vehicle Service Name"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.vehicleServiceName}
            onChange={formik.handleChange}
            error={
              formik.touched.vehicleServiceName &&
              Boolean(formik.errors.vehicleServiceName)
            }
            helperText={
              formik.touched.vehicleServiceName &&
              formik.errors.vehicleServiceName
            }
          />
          <TextField
            type="text"
            id="vehicleServicePrice"
            name="vehicleServicePrice"
            label="Vehicle Service Price"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.vehicleServicePrice}
            onChange={formik.handleChange}
            error={
              formik.touched.vehicleServicePrice &&
              Boolean(formik.errors.vehicleServicePrice)
            }
            helperText={
              formik.touched.vehicleServicePrice &&
              formik.errors.vehicleServicePrice
            }
          />
          <TextField
            type="text"
            id="vehicleServiceDescription"
            name="vehicleServiceDescription"
            label="Dicription"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.vehicleServiceDescription}
            onChange={formik.handleChange}
            error={
              formik.touched.vehicleServiceDescription &&
              Boolean(formik.errors.vehicleServiceDescription)
            }
            helperText={
              formik.touched.vehicleServiceDescription &&
              formik.errors.vehicleServiceDescription
            }
          />
          <TextField
            type="text"
            id="vehicleServiceType"
            name="vehicleServiceType"
            label="Type"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.vehicleServiceType}
            onChange={formik.handleChange}
            error={
              formik.touched.vehicleServiceType &&
              Boolean(formik.errors.vehicleServiceType)
            }
            helperText={
              formik.touched.vehicleServiceType &&
              formik.errors.vehicleServiceType
            }
          />
          <TextField
            type="text"
            id="imageURL"
            name="imageURL"
            label="Image"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.imageURL}
            onChange={formik.handleChange}
            error={formik.touched.imageURL && Boolean(formik.errors.imageURL)}
            helperText={formik.touched.imageURL && formik.errors.imageURL}
          />
        </div>
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

export default EditAddSrService;
