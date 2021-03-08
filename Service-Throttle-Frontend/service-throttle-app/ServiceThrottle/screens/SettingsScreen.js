import AsyncStorage from "@react-native-community/async-storage";
import React from "react";
import { StyleSheet, Text, View } from "react-native";
import { Avatar } from "react-native-elements";
import CustomListItem from "../components/CustomListItem";

import { useDataLayerValue } from "../context/DataLayer";

const SettingsScreen = ({ navigation }) => {
  const [{ username, user }, dispatch] = useDataLayerValue();

  const onpress = (route) => {
    console.log("go to " + route);
  };

  return (
    <View style={styles.container}>
      <View style={styles.topComponent}>
        <Avatar size="large" rounded source={require("../images/avatar.png")} />
        <Text style={styles.userName}>
          {user?.firstname ? user.firstname + " " + user.lastname : username}
        </Text>
        {/* <Text style={styles.otherInfo}>{user ? user.phoneNumber : ""}</Text>
        <Text style={styles.otherInfo}>{user ? user.email : ""}</Text>
        <Text style={styles.otherInfo}>{user ? user.address : ""}</Text> */}
        <Text
          style={styles.editAccount}
          onPress={() => {
            navigation.navigate("Edit Account", { user });
          }}
        >
          EDIT ACCOUNT
        </Text>
      </View>

      <View style={{ marginTop: 20 }}>
        <Text style={styles.titleTexts}>Saved Places</Text>
        <CustomListItem
          _key="1"
          icon="home"
          type="feather"
          title="Home"
          subTitle="Add Home"
          onpress={onpress}
        />
        <CustomListItem
          _key="2"
          icon="briefcase-outline"
          type="ionicon"
          title="Work"
          subTitle="Add Work"
          onpress={onpress}
        />
      </View>
      <View style={{ marginTop: 20 }}>
        <Text style={styles.titleTexts}>Other Options</Text>
        <Text
          style={styles.signOut}
          onPress={async () => {
            await AsyncStorage.clear();
            navigation.reset({ routes: [{ name: "Login" }] });
            // navigation.replace("Login");
          }}
        >
          Sign Out
        </Text>
      </View>
    </View>
  );
};

export default SettingsScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    padding: 10,
  },
  topComponent: {
    marginTop: 70,
    marginBottom: 5,
    alignItems: "center",
  },

  userName: {
    marginTop: 10,
    fontSize: 20,
  },

  titleTexts: {
    marginTop: 10,
    marginLeft: 15,
    marginBottom: 5,
    fontSize: 20,
  },
  editAccount: {
    marginTop: 8,
    fontSize: 15,
    color: "#187bcd",
  },
  signOut: {
    marginTop: 8,
    marginLeft: 15,
    fontSize: 15,
    color: "#187bcd",
  },
});
