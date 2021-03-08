import React, { useState } from "react";
import { FlatList, StyleSheet, Text, View } from "react-native";
import { Input, ListItem } from "react-native-elements";
import { useDataLayerValue } from "../context/DataLayer";

const SearchScreen = ({ navigation }) => {
  const [search, setsearch] = useState("");
  const [{ services }, dispatch] = useDataLayerValue();

  const pressActionService = (service) => {
    navigation.navigate("Service", (service = { service }));
  };

  const keyExtractor = (item) => item.id.toString();

  const renderItem = ({ item }) => {
    if (
      item?.vehicleServiceName.match(new RegExp("\\b" + search + ".*", "i")) ||
      item?.vehicleServiceType.match(new RegExp("\\b" + search + ".*", "i"))
    ) {
      return (
        <ListItem
          key={item.id.toString()}
          bottomDivider
          onPress={async () => {
            pressActionService(item);
          }}
        >
          <ListItem.Content>
            <ListItem.Title>{item?.vehicleServiceName}</ListItem.Title>
            <ListItem.Subtitle style={{ marginTop: 5 }}>
              {"Price : Rs" + item?.vehicleServicePrice + ".00"}
            </ListItem.Subtitle>
            <ListItem.Subtitle
              numberOfLines={2}
              ellipsizeMode="tail"
              style={{ marginTop: 5 }}
            >
              {item?.vehicleServiceDescription}
            </ListItem.Subtitle>
          </ListItem.Content>
        </ListItem>
      );
    }
  };

  return (
    <View style={styles.container}>
      <Input
        inputContainerStyle={styles.input}
        placeholder="Type Here..."
        onChangeText={(text) => {
          setsearch(text);
        }}
      />
      <FlatList
        keyExtractor={keyExtractor}
        data={services}
        renderItem={renderItem}
      />
    </View>
  );
};

export default SearchScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    paddingTop: 10,
  },
  input: {
    width: "100%",
    height: 60,
    borderWidth: 1,
    borderColor: "#104a8e",
    borderRadius: 5,
    paddingLeft: 10,
  },
});
