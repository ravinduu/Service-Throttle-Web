import React, { useLayoutEffect } from "react";
import { Image, StyleSheet, Text, View } from "react-native";
import { Button } from "react-native-elements";

const ServiceItemScreen = (props) => {
  const { navigation } = props;
  const { service } = props.route.params;

  useLayoutEffect(() => {
    navigation.setOptions({
      title: service.vehicleServiceName,
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
            fontSize: 20,
            paddingBottom: 15,
          }}
        >
          Price : Rs.{service.vehicleServicePrice}
        </Text>
        <Text
          style={{
            fontSize: 17,
            paddingBottom: 15,
          }}
        >
          Type : {service.vehicleServiceType}
        </Text>
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
          console.log("shhshh");
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
    marginTop: 20,
    padding: 10,
    borderBottomColor: "gray",
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
