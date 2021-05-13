import React from "react";
import { StyleSheet, Text, View } from "react-native";
import AnimatedEllipsis from "react-native-animated-ellipsis";

const Loading = () => {
  return (
    <View style={styles.container}>
      <Text style={styles.loading}>
        Loading
        <AnimatedEllipsis />
      </Text>
    </View>
  );
};

export default Loading;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  loading: {
    fontSize: 20,
  },
});
