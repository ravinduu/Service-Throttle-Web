import React, { useEffect, useState } from "react";
import { StyleSheet, Text, View } from "react-native";
import AsyncStorage from "@react-native-community/async-storage";
import { Button, Input } from "react-native-elements";
import axios from "axios";

import { useDataLayerValue } from "../context/DataLayer";

const ActivationScreen = () => {
  const [{ api }, dispatch] = useDataLayerValue();
  const [regUsername, setRegUsername] = useState("");
  const [regPassword, setRegPassword] = useState("");
  const [activationCode, setRegActivationCode] = useState("");

  const _activatonRequest = {
    activationCode: "7284",
    password: "1234567890",
    username: "user1",
  };

  const getRegCredentials = async () => {
    await AsyncStorage.getItem("regUsername")
      .then((res) => {
        setRegUsername(res);
      })
      .then(() => {
        AsyncStorage.getItem("regPassword").then((res) => {
          setRegPassword(res);
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const activateAccount = async () => {
    // activatonRequest.username = regUsername;
    // activatonRequest.password = regPassword;
    // activatonRequest.activationCode = activationCode;
    // console.log(activatonRequest);

    await axios
      .get(`${api}/activate`, {
        activationCode: "7284",
        password: "1234567890",
        username: "user1",
      })
      .then((res) => {
        console.log(res.data);
        navigation.navigate("Activate");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    getRegCredentials();
  }, []);

  return (
    <View>
      <Input
        inputContainerStyle={styles.inputContainer}
        placeholder="Activation Code"
        autoFocus
        type="activationCode"
        onChangeText={(text) => {
          setRegActivationCode(text);
        }}
      />
      <Button title="Click me punk" onPress={activateAccount} />
    </View>
  );
};

export default ActivationScreen;

const styles = StyleSheet.create({});
