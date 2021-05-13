import React, { useLayoutEffect } from "react";
import { StyleSheet, Text, View, FlatList, Image } from "react-native";
import { ButtonGroup, Icon, ListItem } from "react-native-elements";
import axios from "axios";
import { AntDesign } from "@expo/vector-icons";

import { useDataLayerValue } from "../context/DataLayer";
import Loading from "./Loading";
import { getRequest } from "../services/requestService";

const RequestScreen = ({ navigation }) => {
  const [{ api, token, user, isLoading, myrequests }, dispatch] =
    useDataLayerValue();

  let authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  const getSRequests = async () => {
    dispatch({
      type: "SET_LOADING",
      isLoading: true,
    });
    await getRequest(authAxios, user?.username)
      .then((res) => {
        dispatch({
          type: "SET_REQ",
          myrequests: res,
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

  const receipt = (item) => {
    navigation.navigate("Receipt", { item });
  };

  const help = () => {
    console.log("help lol");
  };

  const keyExtractor = (item) => item.id.toString();

  const renderItem = ({ item }) => (
    <ListItem
      key={item.id.toString()}
      bottomDivider
      containerStyle={{ borderBottomWidth: 5 }}
    >
      <ListItem.Content>
        <Image
          resizeMode="cover"
          source={{ uri: item.vehicleServices[0].imageURL }}
          style={{ width: "100%", height: 150 }}
        />
        <Text style={{ fontSize: 15, marginTop: 10 }}>
          {item.vehicleServices[0].vehicleServiceName}
        </Text>
        <View style={{ width: "100%" }}>
          <Text
            style={{
              fontSize: 13,
              marginTop: 5,
              color: "gray",
              paddingBottom: 20,
              borderBottomColor: "lightgray",
              borderBottomWidth: 0.5,
            }}
          >
            {item.whenWant + "\nOrder #" + item.id}
          </Text>
        </View>
        <View style={{ flexDirection: "row", marginTop: 10 }}>
          <AntDesign name="user" size={15} color="black" />
          <Text>{" Mechanic   : "}</Text>
          <Text>
            {item.mobileMechanic
              ? item.mobileMechanic.username
              : "Not Assign Yet"}
          </Text>
        </View>
        <View style={{ flexDirection: "row" }}>
          <AntDesign name="user" size={15} color="black" />
          <Text>{" Supervisoe : "}</Text>
          <Text>
            {item.supervisor ? item.supervisor.username : "Not Assign Yet"}
          </Text>
        </View>
        <View
          style={{
            width: "100%",
            paddingBottom: 20,
            borderBottomColor: "lightgray",
            borderBottomWidth: 0.5,
          }}
        ></View>
        <View
          style={{
            width: "100%",
            marginTop: 10,
            paddingBottom: 20,
            borderBottomColor: "lightgray",
            borderBottomWidth: 0.5,
          }}
        >
          <Text>{"Total : Rs " + item.totalCost}</Text>
        </View>
        <View style={{ width: "100%", marginTop: 20 }}>
          <ButtonGroup
            buttons={["View Receipt", "Get Help"]}
            onPress={(index) => {
              if (index == 0) receipt({ item });
              if (index == 1) help();
            }}
          />
        </View>
      </ListItem.Content>
    </ListItem>
  );

  useLayoutEffect(() => {
    const unsubscribe = navigation.addListener("focus", () => {
      getSRequests();
    });

    return unsubscribe;
  }, [navigation]);

  if (isLoading) {
    return <Loading />;
  }

  if (myrequests.length != 0) {
    return (
      <View style={styles.container}>
        <FlatList
          keyExtractor={keyExtractor}
          data={myrequests.reverse()}
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
        You don't have any service requests ...
      </Text>
    </View>
  );
};

export default RequestScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },
});
