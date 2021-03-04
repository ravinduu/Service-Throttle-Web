import React, { useLayoutEffect, useState } from "react";
import { Image, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { Button } from "react-native-elements";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import axios from "axios";

import Popup from "../components/Popup";
import { deleteMyVehicles } from "../services/vehicleService";
import { useDataLayerValue } from "../context/DataLayer";

const MyCarScreen = (props) => {
  const { navigation } = props;
  const item = props.route.params.item;

  const [{ api, token }, dispatch] = useDataLayerValue();

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
    navigation.setOptions({
      title: "",
      headerRight: () => {
        return (
          <TouchableOpacity onPress={toggleModal}>
            <View style={{ marginRight: 20 }}>
              <MaterialCommunityIcons name="delete" size={26} color="black" />
            </View>
          </TouchableOpacity>
        );
      },
    });
  }, [navigation]);

  return (
    <View style={styles.container}>
      <Image
        source={require("../assets/car.jpg")}
        style={{ width: "100%", height: "35%" }}
      />
      <Text
        style={{
          fontSize: 35,
          fontWeight: "600",
          color: "#03254c",
          justifyContent: "flex-start",
        }}
      >
        {item.vehicleMake.make + " " + item.vehicleModel.model}
      </Text>
      <Text
        style={{
          fontSize: 18,
          marginTop: 35,
          justifyContent: "flex-start",
          color: "gray",
        }}
      >
        {"Year : " +
          item.year +
          "\n" +
          "Engine : " +
          item.vehicleEngine.engine +
          "\nLast service date : --/--/---- \nNext Service Date : --/--/----\nNumber of services had : --"}
      </Text>
      <Button
        buttonStyle={{
          borderWidth: 1,
          borderColor: "#104a8e",
          height: 50,
          marginTop: "40%",
        }}
        titleStyle={{ color: "#104a8e" }}
        containerStyle={styles.button}
        type="outline"
        onPress={() => {
          console.log("shhshh");
        }}
        title="View Service Requests"
      />
      <Button
        buttonStyle={{ backgroundColor: "#104a8e", height: 50, marginTop: 20 }}
        containerStyle={styles.button}
        onPress={() => {
          navigation.navigate("By Car", { item });
        }}
        title="Create Service Request"
      />
      <Popup visible={isModalVisible}>
        <Text style={{ fontSize: 20, textAlign: "center", marginBottom: 15 }}>
          {"Are you sure you want to delete " + item.vehicleMake.make}
        </Text>
        <Button
          buttonStyle={{
            borderWidth: 1,
            borderColor: "tomato",
            height: 50,
          }}
          titleStyle={{ color: "tomato" }}
          containerStyle={styles.button}
          type="outline"
          title="Delete"
          onPress={async () => {
            await deleteMyVehicles(authAxios, item.id)
              .then((res) => {
                console.log(res);
                toggleModal();
              })
              .then(() => {
                navigation.goBack();
              });
          }}
        />
        <Button
          buttonStyle={{
            backgroundColor: "#104a8e",
            height: 50,
            marginTop: 10,
          }}
          containerStyle={styles.button}
          title="Cancel"
          onPress={toggleModal}
        />
      </Popup>
    </View>
  );
};

export default MyCarScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    padding: 20,
  },
  button: {
    justifyContent: "flex-end",
    width: "100%",
    position: "relative",
  },
});
