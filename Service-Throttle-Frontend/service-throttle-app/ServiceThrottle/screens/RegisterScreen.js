import React, { useState } from "react";
import { KeyboardAvoidingView, StyleSheet, Text, View } from "react-native";
import { Button, Input } from "react-native-elements";
import { useFormik } from "formik";
import * as yup from "yup";
import axios from "axios";

import { useDataLayerValue } from "../context/DataLayer";

const regCredentials = {
  username: "",
  password: "",
  email: "",
};

const validationSchema = yup.object({
  username: yup
    .string("Enter your username")
    .min(5, "Username should be of minimum 5 characters length")
    .max(50, "Too Long!")
    .required("Username is required"),
  password: yup
    .string("Enter your password")
    .min(8, "Password should be of minimum 8 characters length")
    .max(50, "Too Long!")
    .required("Password is required"),
  email: yup.string().email("Invalid email").required("Required"),
});

const RegisterScreen = ({ navigation }) => {
  const [{ api }, dispatch] = useDataLayerValue();

  const formik = useFormik({
    initialValues: regCredentials,
    validationSchema: validationSchema,
    onSubmit: async (values) => {
      values.accountType = "customer_account";
      console.log(values);
      await axios
        .post(`${api}/register`, values)
        .then((res) => {
          console.log(res.data);
          navigation.navigate("Activate");
        })
        .catch((err) => {
          console.log(err);
        });
    },
  });

  return (
    <KeyboardAvoidingView style={styles.container} behavior="height">
      <Text style={styles.headerText}>Service Throttle</Text>
      <Text style={styles.headerTextTwo}>at your door step.</Text>
      <View>
        <Input
          inputContainerStyle={styles.inputContainer}
          placeholder="Username"
          autoFocus
          type="username"
          value={formik.values.username}
          onChangeText={formik.handleChange("username")}
          errorMessage={formik.touched.username && formik.errors.username}
        />
        <Input
          inputContainerStyle={styles.inputContainer}
          placeholder="Password"
          type="password"
          value={formik.values.password}
          secureTextEntry
          onChangeText={formik.handleChange("password")}
          errorMessage={formik.touched.password && formik.errors.password}
        />
        <Input
          inputContainerStyle={styles.inputContainer}
          placeholder="Email"
          type="email"
          value={formik.values.email}
          onChangeText={formik.handleChange("email")}
          errorMessage={formik.touched.email && formik.errors.email}
        />
      </View>
      <Button
        buttonStyle={{
          backgroundColor: "#104a8e",
        }}
        containerStyle={styles.button}
        title="Sign Up"
        onPress={formik.handleSubmit}
      />
      <View style={{ height: 100 }}></View>
    </KeyboardAvoidingView>
  );
};

export default RegisterScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  inputContainer: {
    width: 330,
    height: 50,
    borderWidth: 1,
    borderColor: "#104a8e",
    borderRadius: 5,
    paddingLeft: 10,
  },
  headerText: {
    color: "#03254c",
    fontSize: 40,
  },
  headerTextTwo: {
    color: "#03254c",
    fontSize: 25,
    marginBottom: 50,
  },
  button: {
    paddingTop: 5,
    width: 200,
    height: 50,
  },
});
