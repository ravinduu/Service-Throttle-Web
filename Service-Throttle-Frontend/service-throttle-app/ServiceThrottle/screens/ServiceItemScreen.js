import React, { useLayoutEffect } from "react";
import { Image, StyleSheet, Text, View } from "react-native";
import { Button, Icon } from "react-native-elements";

const ServiceItemScreen = (props) => {
  const { navigation } = props;
  const { service } = props.route.params;

  useLayoutEffect(() => {
    navigation.setOptions({
      title: "",
    });
  }, [navigation]);

  return (
    <View style={styles.container}>
      <Image
        source={{ uri: service.imageURL }}
        style={{ width: "100%", height: "35%" }}
      />
      <View style={styles.info}>
        <Text
          style={{
            fontSize: 35,
            paddingBottom: 5,
            color: "#03254c",
          }}
        >
          {service.vehicleServiceName}
        </Text>
        <Text
          style={{
            fontSize: 18,
            paddingBottom: 5,
          }}
        >
          Price : Rs.{service.vehicleServicePrice}
        </Text>
        <Text
          style={{
            fontSize: 17,
            paddingBottom: 5,
          }}
        >
          Type : {service.vehicleServiceType}
        </Text>
        <View style={{ flexDirection: "row", paddingBottom: 10 }}>
          <Text>
            {"Ratings : " + (Math.random() * (5 - 4) + 4).toFixed(1) + " "}
          </Text>
          <Icon name="star" size={15} />
          <Text>
            {" (" + Math.floor(Math.random() * (180 - 150) + 150) + ")"}
          </Text>
        </View>
        <Text
          style={{
            fontSize: 17,
            color: "gray",
          }}
        >
          {service.vehicleServiceDescription}
        </Text>
      </View>
      <Button
        buttonStyle={{ backgroundColor: "#104a8e", height: 50 }}
        containerStyle={styles.button}
        onPress={() => {
          navigation.navigate("By Service", { service });
        }}
        title="Create Service Request"
      />
    </View>
  );
};

export default ServiceItemScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },
  info: {
    marginTop: 10,
    marginLeft: 20,
    marginRight: 20,
  },
  button: {
    flex: 1,
    justifyContent: "flex-end",
    padding: 20,
    width: "100%",
    position: "relative",
    marginBottom: 20,
  },
});
