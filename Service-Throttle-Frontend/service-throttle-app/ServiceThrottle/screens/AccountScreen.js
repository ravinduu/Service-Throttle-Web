import React from "react";
import { StyleSheet, View } from "react-native";
import CustomListItem from "../components/CustomListItem";

const AccountScreen = ({ navigation }) => {
  const onpress = (route) => {
    navigation.navigate(route);
  };

  return (
    <View style={styles.container}>
      <CustomListItem
        _key="1"
        icon="settings-sharp"
        type="ionicon"
        title="Settings"
        onpress={onpress}
      />
      <CustomListItem
        key="2"
        icon="tag"
        type="ant-design"
        title="Promotions"
        onpress={onpress}
      />
      {/* <CustomListItem
        key="3"
        icon="live-help"
        type="material-icons"
        title="Help"
      />
      <CustomListItem
        key="4"
        icon="stars"
        type="material-icons"
        title="Join with Service Throttle"
      />
      <CustomListItem
        key="5"
        icon="information-circle"
        type="ionicon"
        title="About"
      /> */}
    </View>
  );
};

export default AccountScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },
});
