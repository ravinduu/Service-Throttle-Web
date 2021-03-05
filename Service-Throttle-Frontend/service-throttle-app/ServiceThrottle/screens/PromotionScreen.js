import AsyncStorage from "@react-native-community/async-storage";
import React, { useLayoutEffect, useState } from "react";
import { Alert, FlatList, StyleSheet, Text, View } from "react-native";
import { Button, Input, ListItem } from "react-native-elements";
import Popup from "../components/Popup";
import { useDataLayerValue } from "../context/DataLayer";

const PromotionScreen = ({ navigation }) => {
  const [code, setcode] = useState("");
  const [used, setUsed] = useState();
  const [dis, setDis] = useState(0);
  const [{ promotions }, dispatch] = useDataLayerValue();
  const [isModalVisible, setModalVisible] = useState(false);

  const toggleModal = () => {
    setModalVisible(!isModalVisible);
  };

  const recentPromo = async () => {
    await AsyncStorage.getItem("RECENT_PROMO").then((res) => {
      setUsed(JSON.parse(res));
    });
  };

  useLayoutEffect(() => {
    recentPromo();
    navigation.setOptions({
      title: "Add Promotion",
    });
  }, [navigation]);

  const keyExtractor = (item) => item.id.toString();

  const renderItem = ({ item }) => {
    if (used?.promoCode !== code && item.promoCode === code) {
      return (
        <ListItem
          key={item.id.toString()}
          bottomDivider
          onPress={async () => {
            setDis(item.discount);
            await AsyncStorage.setItem("RECENT_PROMO", JSON.stringify(item));
            toggleModal();
          }}
        >
          <ListItem.Content>
            <ListItem.Title>{item.promoCode}</ListItem.Title>
            <ListItem.Subtitle style={{ marginTop: 5 }}>
              {item.promoDescription}
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
        placeholder="Enter Promo Code ..."
        onChangeText={(text) => {
          setcode(text);
        }}
      />

      <FlatList
        keyExtractor={keyExtractor}
        data={promotions}
        renderItem={renderItem}
      />
      <Popup visible={isModalVisible}>
        <Text style={{ fontSize: 20, textAlign: "center", marginBottom: 15 }}>
          {"You will get " +
            dis +
            "% off discount with your next Service Request .."}
        </Text>
        <Button
          buttonStyle={{
            borderWidth: 1,
            borderColor: "#104a8e",
            height: 50,
          }}
          titleStyle={{ color: "#104a8e" }}
          containerStyle={{ width: "100%" }}
          type="outline"
          onPress={() => {
            toggleModal();
            navigation.navigate("Account");
          }}
          title="OK"
        />
      </Popup>
    </View>
  );
};

export default PromotionScreen;

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
