import React, { useLayoutEffect, useState } from "react";
import { Picker } from "@react-native-picker/picker";
import { StyleSheet, Text, View } from "react-native";
import DropDownPicker from "react-native-dropdown-picker";
import { useDataLayerValue } from "../context/DataLayer";
import { Button, Input } from "react-native-elements";
import { KeyboardAvoidingView } from "react-native";

const AddCarScreen = ({ navigation }) => {
  useLayoutEffect(() => {
    navigation.setOptions({ title: "" });
  }, [navigation]);
  const [{ engines, makes, models }, dispatch] = useDataLayerValue();

  const [selectedValueYear, setSelectedValueYear] = useState();
  const [selectedValue, setSelectedValue] = useState({});
  const [selectedValueMakes, setSelectedValueMakes] = useState({});
  const [selectedValueModels, setSelectedValueModels] = useState({});

  const [customerVehicle, setCustomerVehicle] = useState({});

  return (
    <KeyboardAvoidingView style={styles.container}>
      <Text
        style={{
          marginTop: 50,
          marginBottom: 50,
          fontSize: 30,
          color: "#03254c",
          justifyContent: "flex-start",
        }}
      >
        Add Your Vehicle
      </Text>

      <View style={styles.pisckerStyle}>
        <Input
          inputContainerStyle={{ height: 50, width: "100%" }}
          placeholder="Year"
          keyboardType="phone-pad"
          secureTextEntry
        />
      </View>
      <View style={styles.pisckerStyle}>
        <Picker
          selectedValue={selectedValueMakes}
          onValueChange={(itemValue, itemIndex) => {
            setSelectedValueMakes(itemValue);
          }}
        >
          <Picker.Item key={0} label="Make" />

          {makes.map((m) => {
            return (
              <Picker.Item key={m.id.toString()} label={m.make} value={m} />
            );
          })}
        </Picker>
      </View>
      <View style={styles.pisckerStyle}>
        <Picker
          selectedValue={selectedValueModels}
          onValueChange={(itemValue, itemIndex) => {
            setSelectedValueModels(itemValue);
          }}
        >
          <Picker.Item key={0} label="Model" />

          {models.map((m) => {
            if (
              selectedValueMakes &&
              m.vehicleMake.id == selectedValueMakes.id
            ) {
              return (
                <Picker.Item key={m.id.toString()} label={m.model} value={m} />
              );
            }
          })}
        </Picker>
      </View>
      <View style={styles.pisckerStyle}>
        <Picker
          selectedValue={selectedValue}
          onValueChange={(itemValue, itemIndex) => {
            setSelectedValue(itemValue);
          }}
        >
          <Picker.Item key={0} label="Engine" />

          {engines.map((e) => {
            if (selectedValueModels) {
              return (
                <Picker.Item key={e.id.toString()} label={e.engine} value={e} />
              );
            }
          })}
        </Picker>
      </View>
      <Button
        buttonStyle={{ backgroundColor: "#104a8e", height: 50 }}
        containerStyle={styles.button}
        onPress={() => {
          console.log("shhshh");
        }}
        title="Submit"
      />
    </KeyboardAvoidingView>
  );
};

export default AddCarScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    backgroundColor: "#fff",
  },
  pisckerStyle: {
    borderRadius: 5,
    borderWidth: 1,
    borderColor: "#104a8e",
    overflow: "hidden",
    height: 50,
    width: "80%",
    marginBottom: 20,
  },
  button: {
    flex: 1,
    justifyContent: "flex-end",
    padding: 20,
    width: "100%",
    marginBottom: 20,
  },
});
