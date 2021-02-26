import React, { useEffect, useLayoutEffect } from "react";
import { StyleSheet, Text } from "react-native";
import axios from "axios";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { getFocusedRouteNameFromRoute } from "@react-navigation/native";

import { FontAwesome5 } from "@expo/vector-icons";
import { MaterialIcons } from "@expo/vector-icons";
import { Ionicons } from "@expo/vector-icons";
import { MaterialCommunityIcons } from "@expo/vector-icons";

import { useDataLayerValue } from "../context/DataLayer";
import { getCurrentUser } from "../services/userService";
import { getPromotions, getServices } from "../services/stService";
import HomeScreen from "./HomeScreen";
import SearchScreen from "./SearchScreen";
import RequestScreen from "./RequestScreen";
import MyVehiclsScreen from "./MyVehiclsScreen";
import AccountScreen from "./AccountScreen";
import { View } from "react-native";
import { Avatar } from "react-native-elements";

const Tab = createBottomTabNavigator();

const Main = ({ navigation, route }) => {
  const [{ api, token, username, user }, dispatch] = useDataLayerValue();

  function getHeaderTitle(route) {
    const routeName = getFocusedRouteNameFromRoute(route) ?? "Home";
    switch (routeName) {
      case "Home":
        return "Home";
      case "Search":
        return "Search";
      case "Requests":
        return "Requests";
      case "My Vehicles":
        return "My Vehicles";
      case "Account":
        return user ? user.firstname + " " + user.lastname : username;
    }
  }

  function getHeaderLeft(route) {
    const routeName = getFocusedRouteNameFromRoute(route);
    console.log(routeName);
    switch (routeName) {
      case "Account":
        return (
          <View style={{ marginLeft: 20 }}>
            <Avatar rounded source={require("../images/avatar.png")} />
          </View>
        );

      default:
        return "";
    }
  }

  useLayoutEffect(() => {
    navigation.setOptions({
      headerTitle: getHeaderTitle(route),
      headerLeft: () => getHeaderLeft(route),
    });
    return () => {
      console.log("unmounting account...");
    };
  }, [navigation, route]);

  let authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  const fetchData = async () => {
    await getCurrentUser(authAxios, username)
      .then((res) => {
        dispatch({
          type: "SET_USER",
          user: res,
        });
      })
      .then(async () => {
        await getServices(authAxios).then((res) => {
          dispatch({
            type: "SET_SERVICES",
            services: res,
          });
        });
      })
      .then(async () => {
        await getPromotions(authAxios).then((res) => {
          dispatch({
            type: "SET_PROMOS",
            promotions: res,
          });
        });
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
    console.log(user);
    if (!user) {
      dispatch({
        type: "SET_LOADING",
        isLoading: true,
      });
      fetchData();
    }

    return () => {
      console.log("unmounting...");
    };
  }, [token, username, api]);

  return (
    <Tab.Navigator
      initialRouteName="Home"
      backBehavior="initialRoute"
      tabBarOptions={{
        activeTintColor: "#104a8e",
        inactiveTintColor: "gray",
      }}
    >
      <Tab.Screen
        name="Home"
        component={HomeScreen}
        options={{
          tabBarIcon: ({ color, size }) => (
            <Ionicons name="home" size={26} color={color} />
          ),
        }}
      />
      <Tab.Screen
        name="Search"
        component={SearchScreen}
        options={{
          tabBarIcon: ({ color, size }) => (
            <FontAwesome5 name="search" size={26} color={color} />
          ),
        }}
      />
      <Tab.Screen
        name="Requests"
        component={RequestScreen}
        options={{
          tabBarIcon: ({ color, size }) => (
            <MaterialIcons name="request-quote" size={26} color={color} />
          ),
        }}
      />
      <Tab.Screen
        name="My Vehicles"
        component={MyVehiclsScreen}
        options={{
          tabBarIcon: ({ color, size }) => (
            <FontAwesome5 name="car" size={26} color={color} />
          ),
        }}
      />
      <Tab.Screen
        name="Account"
        component={AccountScreen}
        options={{
          tabBarIcon: ({ color, size }) => (
            <MaterialCommunityIcons name="account" size={26} color={color} />
          ),
        }}
      />
    </Tab.Navigator>
  );
};

export default Main;

const styles = StyleSheet.create({});
