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
import { KeyboardAvoidingView } from "react-native";
import Popup from "../components/Popup";

const RequesrByVehicle = (props) => {
  const { navigation } = props;
  const { item } = props.route.params;

  const [{ services, api, token }, dispatch] = useDataLayerValue();

  const [service, setService] = useState({});
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

  const recentPromo = async () => {
    await AsyncStorage.getItem("RECENT_PROMO").then((res) => {
      setPromotion(JSON.parse(res));
    });
  };

  const clear = async () => {
    await AsyncStorage.removeItem("RECENT_PROMO");
  };

  const [isModalVisible, setModalVisible] = useState(false);

  const toggleModal = () => {
    setModalVisible(!isModalVisible);
  };

  let authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  useLayoutEffect(() => {
    recentPromo();

    navigation.setOptions({
      title: "",
    });
  }, [navigation]);

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
          height: 60,
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
          if (!service.id) {
            Alert.alert(
              "Not Complete yet!",
              "Plese select your services before submit!"
            );
          } else {
            if (promotion) {
              serviceRequest.discount = promotion.discount;
            }
            (serviceRequest.customerVehicle = item),
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
    marginTop: "25%",
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
