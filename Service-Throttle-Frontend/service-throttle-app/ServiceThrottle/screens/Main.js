import React, { useEffect, useLayoutEffect } from "react";
import { StyleSheet, TouchableOpacity } from "react-native";
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
import { getEngines, getMakes, getModels } from "../services/vehicleService";

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
        return user?.firstname
          ? user.firstname + " " + user.lastname
          : username;
    }
  }

  function getHeaderLeft(route) {
    const routeName = getFocusedRouteNameFromRoute(route);
    switch (routeName) {
      case "Account":
        return (
          <View style={{ marginLeft: 20 }} pointerEvents="none">
            <Avatar rounded source={require("../images/avatar.png")} />
          </View>
        );

      default:
        return "";
    }
  }

  function getHeaderRight(route) {
    const routeName = getFocusedRouteNameFromRoute(route);
    switch (routeName) {
      case "My Vehicles":
        return (
          <TouchableOpacity
            onPress={() => {
              navigation.navigate("Add Vehicle");
            }}
          >
            <View style={{ marginRight: 20 }}>
              <Ionicons name="ios-add" size={35} color="black" />
            </View>
          </TouchableOpacity>
        );

      default:
        return "";
    }
  }

  useLayoutEffect(() => {
    const unsubscribe = navigation.setOptions({
      headerTitle: getHeaderTitle(route),
      headerLeft: () => getHeaderLeft(route),
      headerRight: () => getHeaderRight(route),
    });
    return () => {
      unsubscribe;
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
        await getEngines(authAxios).then((res) => {
          dispatch({
            type: "SET_ENGINES",
            engines: res,
          });
        });
      })
      .then(async () => {
        await getMakes(authAxios).then((res) => {
          dispatch({
            type: "SET_MAKES",
            makes: res,
          });
        });
      })
      .then(async () => {
        await getModels(authAxios).then((res) => {
          dispatch({
            type: "SET_MODELS",
            models: res,
          });
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    if (!user) {
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
            <Ionicons name="ios-document" size={26} color={color} />
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
