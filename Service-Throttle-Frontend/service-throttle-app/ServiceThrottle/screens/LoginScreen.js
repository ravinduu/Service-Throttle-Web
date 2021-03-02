import React, { useEffect, useLayoutEffect } from "react";
import { KeyboardAvoidingView, StyleSheet, View } from "react-native";
import { Button, Image, Input } from "react-native-elements";
import { useFormik } from "formik";
import * as yup from "yup";
import axios from "axios";
import AsyncStorage from "@react-native-community/async-storage";

import { useDataLayerValue } from "../context/DataLayer";
import Loading from "./Loading";

const authCredentials = {
  username: "",
  password: "",
};

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

const LoginScreen = ({ navigation }) => {
  const [{ api, isLoading }, dispatch] = useDataLayerValue();

  const isRegisterd = async () => {
    console.log("is reg");
    await AsyncStorage.getItem("regPassword").then((res) => {
      if (res) {
        navigation.navigate("Activate");
      }
    });
  };

  const isLogged = async () => {
    console.log("is log");
    await AsyncStorage.getItem("USERNAME").then((res) => {
      if (res) {
        dispatch({
          type: "SET_USERNAME",
          username: res,
        });
      }
    });

    await AsyncStorage.getItem("JWT").then((res) => {
      if (res) {
        dispatch({
          type: "SET_TOKEN",
          token: res,
        });
        navigation.replace("Main");
      }
    });
  };

  useLayoutEffect(() => {
    dispatch({
      type: "SET_LOADING",
      isLoading: true,
    });
    isLogged();
    isRegisterd();
    dispatch({
      type: "SET_LOADING",
      isLoading: false,
    });
    return () => {
      dispatch({
        type: "SET_LOADING",
        isLoading: false,
      });
    };
  }, []);

  const formik = useFormik({
    initialValues: authCredentials,
    validationSchema: validationSchema,
    onSubmit: async (values) => {
      await axios
        .post(`${api}/login`, values)
        .then((res) => {
          const _token = res.data.jwttoken;
          const _username = res.data.username;
          AsyncStorage.setItem("JWT", _token);
          AsyncStorage.setItem("USERNAME", _username);
          dispatch({
            type: "SET_TOKEN",
            token: _token,
          });
          dispatch({
            type: "SET_USERNAME",
            username: _username,
          });
          navigation.replace("Main");
        })
        .catch((err) => {
          console.log(err);
        });
    },
  });

  if (isLoading) {
    return <Loading />;
  }

  return (
    <KeyboardAvoidingView style={styles.container}>
      <Image
        source={require("../images/1024.png")}
        style={{ width: 150, height: 150 }}
      />
      <View>
        <Input
          inputContainerStyle={styles.inputContainer}
          placeholder="Username"
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
      </View>
      <Button
        buttonStyle={{
          backgroundColor: "#104a8e",
        }}
        containerStyle={styles.button}
        onPress={formik.handleSubmit}
        title="Login"
      />
      <Button
        buttonStyle={{
          borderColor: "#104a8e",
        }}
        titleStyle={{ color: "#104a8e" }}
        containerStyle={styles.button}
        type="outline"
        title="Sign Up"
        onPress={() => navigation.navigate("Register")}
      />
    </KeyboardAvoidingView>
  );
};

export default LoginScreen;

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
  button: {
    paddingTop: 5,
    width: 200,
    height: 50,
  },
});
