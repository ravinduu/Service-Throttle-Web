import React, { useLayoutEffect, useState } from "react";
import { StyleSheet, Text, View, KeyboardAvoidingView } from "react-native";
import { useFormik } from "formik";
import * as yup from "yup";
import axios from "axios";

import { useDataLayerValue } from "../context/DataLayer";
import { Button, Input } from "react-native-elements";
import { editCurrentUser, getCurrentUser } from "../services/userService";

const CompleteUserScreen = (props) => {
  const [{ api, token, user, username }, dispatch] = useDataLayerValue();
  const { navigation } = props;
  let initialVal;

  let authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  const validationSchema = yup.object({
    firstname: yup
      .string("Enter your firstname")
      .required("First Name is required"),
    lastname: yup
      .string("Enter your lastname")
      .required("Last Name is required"),
    phoneNumber: yup
      .string("Enter your phone number")
      .min(10, "Invalid phone number")
      .max(10, "Invalid phone number")
      .required("Phone number is required"),
    address: yup.string("Enter your address").required("Address is required"),
  });

  const formik = useFormik({
    initialValues: user,
    validationSchema: validationSchema,
    onSubmit: async (values) => {
      console.log(values);
      editCurrentUser(authAxios, values).then(() => {
        getCurrentUser(authAxios, username)
          .then((res) => {
            dispatch({
              type: "SET_USER",
              user: res,
            });
          })
          .then(() => {
            navigation.replace("Main");
          });
      });
    },
  });

  return (
    <KeyboardAvoidingView style={styles.container}>
      <View>
        <View style={{ height: 50 }}></View>

        <Input
          inputContainerStyle={styles.inputContainer}
          placeholder="First Name"
          type="firstname"
          value={formik.values.firstname}
          onChangeText={formik.handleChange("firstname")}
          errorMessage={formik.touched.firstname && formik.errors.firstname}
        />
        <Input
          inputContainerStyle={styles.inputContainer}
          placeholder="Last Name"
          type="lastname"
          value={formik.values.lastname}
          onChangeText={formik.handleChange("lastname")}
          errorMessage={formik.touched.lastname && formik.errors.lastname}
        />
        <Input
          inputContainerStyle={styles.inputContainer}
          placeholder="Phone Number"
          type="phoneNumber"
          value={formik.values.phoneNumber}
          onChangeText={formik.handleChange("phoneNumber")}
          errorMessage={formik.touched.phoneNumber && formik.errors.phoneNumber}
        />
        <Input
          inputContainerStyle={styles.inputContainer}
          placeholder="Address"
          type="address"
          value={formik.values.address}
          onChangeText={formik.handleChange("address")}
          errorMessage={formik.touched.address && formik.errors.address}
        />
      </View>
      <Button
        buttonStyle={{
          backgroundColor: "#104a8e",
          height: 50,
        }}
        containerStyle={styles.button}
        title="Save"
        onPress={formik.handleSubmit}
      />
      <View style={{ height: 50 }}></View>
    </KeyboardAvoidingView>
  );
};

export default CompleteUserScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    padding: 10,
    justifyContent: "center",
    alignItems: "center",
  },
  inputContainer: {
    width: 330,
    height: 45,
    borderWidth: 1,
    borderColor: "#104a8e",
    borderRadius: 5,
    paddingLeft: 10,
  },
  button: {
    paddingTop: 5,
    width: "95%",
  },
});
