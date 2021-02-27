import React from "react";
import { StyleSheet, Text, View } from "react-native";
import CustomListItem from "../components/CustomListItem";
import { useDataLayerValue } from "../context/DataLayer";

const AccountScreen = ({ navigation }) => {
  const [{ user }, dispatch] = useDataLayerValue();

  const onpress = (route) => {
    navigation.navigate(route);
  };

  return (
    <View style={styles.container}>
      <Text>{user?.username}</Text>
      <CustomListItem
        _key="1"
        icon="settings-sharp"
        type="ionicon"
        title="Settings"
        onpress={onpress}
      />
      {/* <CustomListItem key="2" icon="tag" type="ant-design" title="Promotions" />
      <CustomListItem
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
