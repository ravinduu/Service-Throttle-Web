import React, { useLayoutEffect } from "react";
import { FlatList, StyleSheet, Text, View } from "react-native";
import axios from "axios";

import { useDataLayerValue } from "../context/DataLayer";
import { getMyVehicles } from "../services/vehicleService";
import Loading from "./Loading";
import { ListItem } from "react-native-elements";

const MyVehiclsScreen = ({ navigation }) => {
  const [
    { api, token, user, isLoading, myVehicles },
    dispatch,
  ] = useDataLayerValue();

  let authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  const getMyCars = async () => {
    dispatch({
      type: "SET_LOADING",
      isLoading: true,
    });
    await getMyVehicles(authAxios, user?.username)
      .then((res) => {
        dispatch({
          type: "SET_CAR",
          myVehicles: res,
        });
      })
      .catch((err) => {
        console.log(err);
      });
    dispatch({
      type: "SET_LOADING",
      isLoading: false,
    });
  };

  useLayoutEffect(() => {
    const unsubscribe = navigation.addListener("focus", () => {
      getMyCars();
    });

    return unsubscribe;
  }, [navigation]);

  if (isLoading) {
    return <Loading />;
  }

  const keyExtractor = (item) => item.id.toString();

  const renderItem = ({ item }) => (
    <ListItem
      key={item.id.toString()}
      bottomDivider
      onPress={() => {
        navigation.navigate("My Car", { item });
      }}
    >
      <ListItem.Content>
        <ListItem.Title>
          {item.vehicleMake.make + " " + item.vehicleModel.model}
        </ListItem.Title>
      </ListItem.Content>
      <ListItem.Chevron />
    </ListItem>
  );

  if (myVehicles.length != 0) {
    return (
      <View style={styles.container}>
        <FlatList
          keyExtractor={keyExtractor}
          data={myVehicles.reverse()}
          renderItem={renderItem}
        />
      </View>
    );
  }

  return (
    <View
      style={{
        flex: 1,
        backgroundColor: "#fff",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Text style={{ color: "gray", fontSize: 16 }}>
        You Don't have any vehicles...
      </Text>
    </View>
  );
};

export default MyVehiclsScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },
});
