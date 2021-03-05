import React, { useState, useLayoutEffect } from "react";
import { Image, StyleSheet, Text, View, Alert } from "react-native";
import { Picker } from "@react-native-picker/picker";
import { Button, Input } from "react-native-elements";
import axios from "axios";

import { useDataLayerValue } from "../context/DataLayer";
import { addRequest } from "../services/requestService";

const RequesrByVehicle = (props) => {
  const { navigation } = props;
  const { item } = props.route.params;

  const [{ services, api, token }, dispatch] = useDataLayerValue();

  const [service, setService] = useState({});
  const [_comments, setComments] = useState("");
  const [serviceRequest, setServiceRequest] = useState({
    vehicleServices: [],
    latitude: 2.34433,
    longitude: 3.3434,
  });

  let authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  useLayoutEffect(() => {
    navigation.setOptions({
      title: "",
    });
  }, [navigation]);

  return (
    <View style={styles.container}>
      <Image
        source={require("../assets/service.jpg")}
        style={{ width: "100%", height: "35%" }}
      />
      <Text
        style={{
          fontSize: 25,
          paddingBottom: 20,
          borderBottomColor: "lightgray",
          borderBottomWidth: 1,
        }}
      >
        {item.vehicleMake.make + " " + item.vehicleModel.model}
      </Text>
      <View
        style={{
          flexDirection: "row",
          justifyContent: "space-between",
          paddingBottom: 20,
          borderBottomColor: "lightgray",
          borderBottomWidth: 1,
        }}
      >
        <Text
          style={{
            fontSize: 25,
            paddingTop: 20,
            paddingBottom: 20,
          }}
        >
          {service.vehicleServiceName
            ? service?.vehicleServiceName
            : "Select Service"}
        </Text>
        <View style={styles.pisckerStyle}>
          <Picker
            selectedValue={service}
            onValueChange={(itemValue, itemIndex) => {
              setService(itemValue);
            }}
          >
            <Picker.Item key={0} label="Services" />

            {services.map((s) => {
              return (
                <Picker.Item
                  key={s.id.toString()}
                  label={s.vehicleServiceName}
                  value={s}
                />
              );
            })}
          </Picker>
        </View>
      </View>
      <Input
        inputContainerStyle={{
          width: "100%",
          height: 60,
          borderWidth: 1,
          borderColor: "#104a8e",
          borderRadius: 5,
          paddingLeft: 10,
          marginTop: 30,
        }}
        placeholder="Any Comments..."
        onChangeText={(text) => {
          setComments(text);
        }}
      />

      <Button
        buttonStyle={{ backgroundColor: "#104a8e", height: 50 }}
        containerStyle={styles.button}
        onPress={async () => {
          if (!service.id) {
            Alert.alert(
              "Not Complete yet!",
              "Plese select your services before submit!"
            );
          } else {
            (serviceRequest.customerVehicle = item),
              (serviceRequest.vehicleServices[0] = service),
              (serviceRequest.comments = _comments),
              (serviceRequest.totalCost = service.vehicleServicePrice),
              await addRequest(authAxios, serviceRequest).then((res) =>
                navigation.navigate("Requests")
              );
          }
        }}
        title="Submit"
      />
    </View>
  );
};

export default RequesrByVehicle;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    padding: 20,
  },
  pisckerStyle: {
    marginTop: 20,
    borderColor: "#104a8e",
    overflow: "hidden",
    height: 50,
    width: "15%",
  },
  button: {
    marginTop: "35%",
    width: "100%",
    position: "relative",
  },
});
