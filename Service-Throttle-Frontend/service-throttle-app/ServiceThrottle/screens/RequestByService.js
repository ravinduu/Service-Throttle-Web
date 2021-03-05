import React, { useLayoutEffect, useState } from "react";
import { Alert, Image, StyleSheet, Text, View, Platform } from "react-native";
import axios from "axios";
import AsyncStorage from "@react-native-community/async-storage";
import { Picker } from "@react-native-picker/picker";
import { Button, Input } from "react-native-elements";
import DateTimePicker from "@react-native-community/datetimepicker";
import { MaterialIcons } from "@expo/vector-icons";

import { useDataLayerValue } from "../context/DataLayer";
import { addRequest } from "../services/requestService";
import Loading from "./Loading";
import { getMyVehicles } from "../services/vehicleService";
import { KeyboardAvoidingView } from "react-native";
import Popup from "../components/Popup";

const RequestByService = (props) => {
  const { navigation } = props;
  const { service } = props.route.params;

  const [
    { myVehicles, api, token, isLoading, user },
    dispatch,
  ] = useDataLayerValue();

  const [vehicle, setVehicle] = useState({});
  const [_comments, setComments] = useState("");
  const [serviceRequest, setServiceRequest] = useState({
    discount: 0,
    vehicleServices: [],
    latitude: 2.34433,
    longitude: 3.3434,
  });
  const [promotion, setPromotion] = useState();

  const [date, setDate] = useState(new Date());
  const [mode, setMode] = useState("date");
  const [show, setShow] = useState(false);

  const onChange = (event, selectedDate) => {
    const currentDate = selectedDate || date;
    setShow(Platform.OS === "ios");
    setDate(currentDate);
  };

  const showMode = (currentMode) => {
    setShow(true);
    setMode(currentMode);
  };

  const showDatepicker = () => {
    showMode("date");
  };

  const showTimepicker = () => {
    showMode("time");
  };

  const recentPromo = async () => {
    await AsyncStorage.getItem("RECENT_PROMO").then((res) => {
      setPromotion(JSON.parse(res));
    });
  };

  const clear = async () => {
    await AsyncStorage.removeItem("RECENT_PROMO");
  };

  let authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  const [isModalVisible, setModalVisible] = useState(false);

  const toggleModal = () => {
    setModalVisible(!isModalVisible);
  };

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
    recentPromo();

    navigation.setOptions({
      title: "",
    });

    if (myVehicles.length == 0) {
      getMyCars();
    }
  }, [navigation]);

  if (isLoading) {
    return <Loading />;
  }

  console.log(date);

  return (
    <KeyboardAvoidingView style={styles.container}>
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
        {service.vehicleServiceName}
      </Text>
      <View
        style={{
          flexDirection: "row",
          justifyContent: "space-between",
          borderBottomColor: "lightgray",
          borderBottomWidth: 1,
        }}
      >
        <Text
          style={{
            fontSize: 25,
            paddingTop: 20,
          }}
        >
          {vehicle?.vehicleMake
            ? vehicle.vehicleMake.make + " " + vehicle.vehicleModel.model
            : "Select Vehicle"}
        </Text>
        <View style={styles.pisckerStyle}>
          <Picker
            selectedValue={vehicle}
            onValueChange={(itemValue, itemIndex) => {
              setVehicle(itemValue);
            }}
          >
            <Picker.Item key={0} label="Vehicles" />

            {myVehicles.map((v) => {
              return (
                <Picker.Item
                  key={v.id.toString()}
                  label={v.vehicleMake.make + " " + v.vehicleModel.model}
                  value={v}
                />
              );
            })}
          </Picker>
        </View>
      </View>

      <View
        style={{
          marginVertical: 20,
          marginRight: 10,
          flexDirection: "row",
          justifyContent: "space-between",
        }}
      >
        <Text style={{ fontSize: 25, color: "black" }}>
          {"When " + date.toISOString().substring(0, 10)}
        </Text>
        <Button
          icon={<MaterialIcons name="date-range" size={25} color="gray" />}
          onPress={showDatepicker}
          buttonStyle={{
            height: 40,
            width: 40,
            justifyContent: "flex-end",
            backgroundColor: "white",
          }}
        />
      </View>

      {show && (
        <DateTimePicker
          testID="dateTimePicker"
          value={date}
          mode={mode}
          is24Hour={true}
          display="default"
          onChange={onChange}
        />
      )}

      <Input
        inputContainerStyle={{
          width: "100%",
          height: 50,
          borderWidth: 1,
          borderColor: "#104a8e",
          borderRadius: 5,
          paddingLeft: 10,
          marginTop: 10,
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
          if (!vehicle.id) {
            Alert.alert(
              "Not Complete yet!",
              "Plese select a vehicle before submit!"
            );
          } else {
            if (promotion) {
              serviceRequest.discount = promotion.discount;
            }
            (serviceRequest.customerVehicle = vehicle),
              (serviceRequest.vehicleServices[0] = service),
              (serviceRequest.comments = _comments),
              (serviceRequest.totalCost =
                service.vehicleServicePrice -
                service.vehicleServicePrice * (serviceRequest.discount / 100)),
              (serviceRequest.whenWant = date.toISOString().substring(0, 10));
            toggleModal();
          }
        }}
        title="Submit"
      />
      <Popup visible={isModalVisible}>
        <Text style={{ fontSize: 20, textAlign: "center", marginBottom: 15 }}>
          {serviceRequest?.vehicleServices[0]?.vehicleServiceName +
            " for your " +
            serviceRequest?.customerVehicle?.vehicleMake.make +
            " " +
            serviceRequest?.customerVehicle?.vehicleModel.model}
        </Text>

        <View style={styles.info2}>
          <View>
            <Text style={{ fontSize: 15 }}>Subtotal .................... </Text>
            <Text style={{ fontSize: 15 }}>Discount ................... </Text>
            <Text style={{ fontSize: 15 }}>
              Total .........................{" "}
            </Text>
          </View>
          <View>
            <Text style={{ fontSize: 15 }}>
              {"Rs" +
                serviceRequest?.vehicleServices[0]?.vehicleServicePrice +
                ".00"}
            </Text>
            <Text style={{ fontSize: 15, color: "green" }}>
              {"Rs" +
                (serviceRequest?.vehicleServices[0]?.vehicleServicePrice *
                  serviceRequest?.discount) /
                  100 +
                ".00"}
            </Text>
            <Text style={{ fontSize: 15, color: "black" }}>
              {"Rs" + serviceRequest?.totalCost + ".00"}
            </Text>
          </View>
        </View>
        <Button
          buttonStyle={{
            backgroundColor: "#104a8e",
            height: 50,
          }}
          titleStyle={{ color: "white" }}
          containerStyle={{ width: "100%" }}
          onPress={async () => {
            await addRequest(authAxios, serviceRequest).then((res) =>
              navigation.navigate("Requests")
            );
            await clear();
            toggleModal();
          }}
          title="Confirm"
        />
        <Button
          buttonStyle={{
            borderColor: "#104a8e",
            borderWidth: 1,
            height: 50,
          }}
          titleStyle={{ color: "#104a8e" }}
          containerStyle={{ marginTop: 10, width: "100%" }}
          type="outline"
          onPress={toggleModal}
          title="Cancel"
        />
      </Popup>
    </KeyboardAvoidingView>
  );
};

export default RequestByService;

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
  info2: {
    flexDirection: "row",
    justifyContent: "space-between",
    paddingBottom: 30,
    borderBottomColor: "lightgray",
    borderBottomWidth: 1,
  },
});
