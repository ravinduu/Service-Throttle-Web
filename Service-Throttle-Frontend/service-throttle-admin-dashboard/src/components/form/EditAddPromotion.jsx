import React from "react";
import { useFormik } from "formik";
import * as yup from "yup";
import { makeStyles } from "@material-ui/core/styles";
import { Button, TextField, Grid } from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(1),
    },
  },
}));

function EditAddPromotion(props) {
  const { editPromotion, recordForEdit } = props;
  const classes = useStyles();

  const validationSchema = yup.object({
    promoCode: yup.string("Enter new code").required("PromoCode is required"),
    discount: yup
      .number()
      .max(100, "Maximum Exeeded")
      .min(10, "Add value greater than 10")
      .required("PromoDiscount is required"),
    promoDescription: yup
      .string("Enter new desctiption")
      .required("Promo description is required"),
    imageUri: yup.string("Enter new imageuri"),
  });

  const formik = useFormik({
    initialValues: recordForEdit,
    validationSchema: validationSchema,
    onSubmit: (values) => {
      console.log(values);
      editPromotion(values);
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
            id="promoCode"
            name="promoCode"
            label="PromoCode"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.promoCode}
            onChange={formik.handleChange}
            error={formik.touched.promoCode && Boolean(formik.errors.promoCode)}
            helperText={formik.touched.promoCode && formik.errors.promoCode}
          />
          <TextField
            type="text"
            id="discount"
            name="discount"
            label="Discount"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.discount}
            onChange={formik.handleChange}
            error={formik.touched.discount && Boolean(formik.errors.discount)}
            helperText={formik.touched.discount && formik.errors.discount}
          />
          <TextField
            type="text"
            id="promoDescription"
            name="promoDescription"
            label="Dicription"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.promoDescription}
            onChange={formik.handleChange}
            error={
              formik.touched.promoDescription &&
              Boolean(formik.errors.promoDescription)
            }
            helperText={
              formik.touched.promoDescription && formik.errors.promoDescription
            }
          />
          <TextField
            type="text"
            id="imageUri"
            name="imageUri"
            label="Image"
            margin="dense"
            variant="outlined"
            className="input"
            value={formik.values.imageUri}
            onChange={formik.handleChange}
            error={formik.touched.imageUri && Boolean(formik.errors.imageUri)}
            helperText={formik.touched.imageUri && formik.errors.imageUri}
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

export default EditAddPromotion;
