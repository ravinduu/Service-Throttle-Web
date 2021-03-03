import React from "react";
import { View, Text, Image, StyleSheet } from "react-native";
import { Card } from "react-native-elements";
import { TouchableOpacity } from "react-native";

const CustomCard = (props) => {
  const { title, id, description, imageUri, price, action, service } = props;
  return (
    <TouchableOpacity
      onPress={() => {
        {
          action ? action(service) : "";
        }
      }}
    >
      <Card pointerEvents="none">
        <Card.Title
          style={{ fontSize: 18, fontWeight: "600", color: "#03254c" }}
        >
          {title}
        </Card.Title>
        <Card.Divider />
        <View key={id} style={styles.user}>
          <Image
            resizeMode="cover"
            source={{ uri: imageUri }}
            style={{ width: "100%", height: 150 }}
          />
          <Text style={{ marginTop: 10, marginBottom: 10, fontSize: 15 }}>
            {price}
          </Text>
          <Text
            style={{ color: "gray" }}
            numberOfLines={2}
            ellipsizeMode="tail"
          >
            {description}
          </Text>
        </View>
      </Card>
    </TouchableOpacity>
  );
};

export default CustomCard;

const styles = StyleSheet.create({});
