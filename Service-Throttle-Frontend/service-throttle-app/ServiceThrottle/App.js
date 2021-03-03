import React from "react";
import "react-native-gesture-handler";
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import { SafeAreaProvider } from "react-native-safe-area-context";

import { DataLayer } from "./context/DataLayer";
import { reducer, initialState } from "./context/reducer";
import LoginScreen from "./screens/LoginScreen";
import RegisterScreen from "./screens/RegisterScreen";
import ActivationScreen from "./screens/ActivationScreen";
import Main from "./screens/Main";
import SettingsScreen from "./screens/SettingsScreen";
import ServiceItemScreen from "./screens/ServiceItemScreen";
import AddCarScreen from "./screens/AddCarScreen";
import MyCarScreen from "./screens/MyCarScreen";

const Stack = createStackNavigator();

export default function App() {
  return (
    <SafeAreaProvider>
      <DataLayer initialState={initialState} reducer={reducer}>
        <NavigationContainer>
          <Stack.Navigator>
            <Stack.Screen
              name="Login"
              component={LoginScreen}
              options={{ headerShown: false }}
            />
            <Stack.Screen name="Register" component={RegisterScreen} />
            <Stack.Screen name="Activate" component={ActivationScreen} />
            <Stack.Screen name="Main" component={Main} />
            <Stack.Screen name="Settings" component={SettingsScreen} />
            <Stack.Screen name="Service" component={ServiceItemScreen} />
            <Stack.Screen name="Add Vehicle" component={AddCarScreen} />
            <Stack.Screen name="My Car" component={MyCarScreen} />
          </Stack.Navigator>
        </NavigationContainer>
      </DataLayer>
    </SafeAreaProvider>
  );
}
