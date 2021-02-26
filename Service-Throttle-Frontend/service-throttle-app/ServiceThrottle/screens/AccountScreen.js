import React from "react";
import { StyleSheet, Text, View } from "react-native";
import { useDataLayerValue } from "../context/DataLayer";

const AccountScreen = ({ navigation }) => {
  const [{ user }, dispatch] = useDataLayerValue();

  return (
    <View style={styles.container}>
      <Text>{user?.username}</Text>
    </View>
  );
};

export default AccountScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});
