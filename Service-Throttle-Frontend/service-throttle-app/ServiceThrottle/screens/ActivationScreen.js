import React, { useEffect, useState } from "react";
import { KeyboardAvoidingView, StyleSheet, Text, View } from "react-native";
import AsyncStorage from "@react-native-community/async-storage";
import { Button, Input } from "react-native-elements";
import { useFormik } from "formik";
import * as yup from "yup";
import axios from "axios";

import { useDataLayerValue } from "../context/DataLayer";
import {} from "react-native";

const regCredentials = {
  code: "",
};

const validationSchema = yup.object({
  code: yup
    .string("Enter your code")
    .min(4, "Invalid Code")
    .max(4, "Invalid Code!")
    .required("Activation code is required"),
});

const ActivationScreen = () => {
  const [{ api }, dispatch] = useDataLayerValue();

  const [activatonRequest, setActivatonRequest] = useState({
    activationCode: "",
    password: "",
    username: "",
  });

  const getRegCredentials = async () => {
    await AsyncStorage.getItem("regUsername")
      .then((res) => {
        activatonRequest.username = res;
      })
      .then(() => {
        AsyncStorage.getItem("regPassword").then((res) => {
          activatonRequest.password = res;
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const activateAccount = async () => {
    console.log(activatonRequest);
    await axios
      .post(`${api}/activate`, activatonRequest)
      .then((res) => {
        const _token = res.data.jwttoken;
        dispatch({
          type: "SET_TOKEN",
          token: _token,
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const formik = useFormik({
    initialValues: regCredentials,
    validationSchema: validationSchema,
    onSubmit: async (values) => {
      activatonRequest.activationCode = values.code;
      activateAccount();
    },
  });

  useEffect(() => {
    getRegCredentials();
  }, []);

  return (
    <KeyboardAvoidingView style={styles.container}>
      <Text style={styles.headerText}>Verification.</Text>

      <Text style={styles.headerTextTwo}>
        Enter the 4-digit code we sent to your email to continue.
      </Text>
      <View>
        <Input
          inputContainerStyle={styles.inputContainer}
          placeholder="Activation Code"
          keyboardType="phone-pad"
          autoCorrect={false}
          value={formik.values.code}
          onChangeText={formik.handleChange("code")}
          errorMessage={formik.touched.code && formik.errors.code}
        />
      </View>
      <Button
        buttonStyle={{
          backgroundColor: "#104a8e",
        }}
        containerStyle={styles.button}
        title="Confirm"
        onPress={formik.handleSubmit}
      />
    </KeyboardAvoidingView>
  );
};

export default ActivationScreen;

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
    marginBottom: 10,
  },
  headerTextTwo: {
    color: "#03254c",
    fontSize: 15,
    marginBottom: 50,
  },
  button: {
    paddingTop: 5,
    width: 200,
    height: 50,
  },
});
