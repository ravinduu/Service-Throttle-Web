import React from "react";
import "react-native-gesture-handler";
import { NavigationContainer } from "@react-navigation/native";
import { StyleSheet } from "react-native";
import { createStackNavigator } from "@react-navigation/stack";
import { SafeAreaProvider } from "react-native-safe-area-context";

import { DataLayer } from "./context/DataLayer";
import { reducer, initialState } from "./context/reducer";
import LoginScreen from "./screens/LoginScreen";
import RegisterScreen from "./screens/RegisterScreen";
import ActivationScreen from "./screens/ActivationScreen";

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
          </Stack.Navigator>
        </NavigationContainer>
      </DataLayer>
    </SafeAreaProvider>
  );
}
