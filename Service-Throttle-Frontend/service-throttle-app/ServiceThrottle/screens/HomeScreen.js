import React, { useEffect } from "react";
import { StyleSheet, Text, View } from "react-native";
import axios from "axios";

import { useDataLayerValue } from "../context/DataLayer";
import { getCurrentUser } from "../services/userService";

const HomeScreen = () => {
  const [{ api, token, username }, dispatch] = useDataLayerValue();

  let authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  const fetchData = async () => {
    await getCurrentUser(authAxios, username)
      .then((res) => {
        console.log(res);
        dispatch({
          type: "SET_USER",
          user: res,
        });
      })
      .then(async () => {
        console.log("get services..");
      })
      .then(async () => {
        console.log("get promotions..");
      })
      .then(async () => {
        console.log("get CustomerVehicles..");
      })
      .then(async () => {
        console.log("get Service Requests..");
      })
      .then(async () => {
        console.log("get Parts..");
      })
      .then(() => {
        dispatch({
          type: "SET_LOADING",
          isLoading: false,
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    dispatch({
      type: "SET_LOADING",
      isLoading: true,
    });
    fetchData();

    return () => {
      console.log("Mehhh unmounting...");
    };
  }, [token, username, api]);

  return (
    <View>
      <Text>{username}</Text>
    </View>
  );
};

export default HomeScreen;

const styles = StyleSheet.create({});
