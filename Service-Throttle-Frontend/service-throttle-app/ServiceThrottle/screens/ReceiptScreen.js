import React from "react";
import { ImageBackground, StyleSheet, Text, View } from "react-native";

const ReceiptScreen = (props) => {
  const { item } = props.route.params.item;

  return (
    <View style={styles.container}>
      <ImageBackground
        source={require("../assets/receipt.jpg")}
        style={{ width: "100%", height: "60%" }}
      >
        <View style={{ marginLeft: 20, marginTop: 20, marginRight: 20 }}>
          <Text style={{ fontSize: 15 }}>{item?.whenWant}</Text>
          <Text style={{ fontSize: 30 }}>
            {"Here's Your reciept\nfor " +
              item?.vehicleServices[0]?.vehicleServiceName +
              "."}
          </Text>
          <View style={styles.info}>
            <Text style={{ fontSize: 25 }}>
              {"for " +
                item?.customerVehicle?.vehicleMake?.make +
                " " +
                item?.customerVehicle?.vehicleModel?.model}
            </Text>
          </View>
          <View style={styles.info2}>
            <Text style={{ fontSize: 25 }}>Total</Text>
            <Text style={{ fontSize: 25 }}>
              {"Rs" + item?.totalCost + ".00"}
            </Text>
          </View>
          <View style={styles.info2}>
            <View>
              <Text style={{ fontSize: 15 }}>Subtotal</Text>
              <Text style={{ fontSize: 15 }}>Discount</Text>
            </View>
            <View>
              <Text style={{ fontSize: 15 }}>
                {"Rs" + item?.vehicleServices[0]?.vehicleServicePrice + ".00"}
              </Text>
              <Text style={{ fontSize: 15, color: "green" }}>
                {"Rs" +
                  (item?.vehicleServices[0]?.vehicleServicePrice *
                    item?.discount) /
                    100 +
                  ".00"}
              </Text>
            </View>
          </View>
        </View>
      </ImageBackground>
    </View>
  );
};

export default ReceiptScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },
  info: {
    marginTop: "40%",
    flexDirection: "row",
    justifyContent: "space-between",
    paddingBottom: 30,
    borderBottomColor: "lightgray",
    borderBottomWidth: 1,
  },
  info2: {
    marginTop: 30,
    flexDirection: "row",
    justifyContent: "space-between",
    paddingBottom: 30,
    borderBottomColor: "lightgray",
    borderBottomWidth: 1,
  },
});
