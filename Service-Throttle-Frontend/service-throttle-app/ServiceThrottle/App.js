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
import RequesrByVehicle from "./screens/RequesrByVehicle";
import RequestByService from "./screens/RequestByService";
import ReceiptScreen from "./screens/ReceiptScreen";
import PromotionScreen from "./screens/PromotionScreen";
import EditAccountScreen from "./screens/EditAccountScreen";
import CompleteUserScreen from "./screens/CompleteUserScreen";

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
            <Stack.Screen name="By Service" component={RequestByService} />
            <Stack.Screen name="By Car" component={RequesrByVehicle} />
            <Stack.Screen name="Receipt" component={ReceiptScreen} />
            <Stack.Screen name="Promotions" component={PromotionScreen} />
            <Stack.Screen name="Edit Account" component={EditAccountScreen} />
            <Stack.Screen
              name="Complete Account"
              component={CompleteUserScreen}
            />
          </Stack.Navigator>
        </NavigationContainer>
      </DataLayer>
    </SafeAreaProvider>
  );
}
