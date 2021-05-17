import React from "react";
import { FlatList, StyleSheet, Text, View } from "react-native";
import { ScrollView } from "react-native-gesture-handler";
import CustomCard from "../components/CustomCard";

import { useDataLayerValue } from "../context/DataLayer";

const HomeScreen = ({ navigation }) => {
  const [{ services, promotions }, dispatch] = useDataLayerValue();

  const pressActionService = (service) => {
    navigation.navigate("Service", (service = { service }));
  };

  const renderPromos = ({ item }) => (
    <CustomCard
      title={item.promoCode}
      id={item.id}
      price={""}
      description={item.promoDescription}
      // imageUri={item.imageUri}
    />
  );

  return (
    <ScrollView
      style={styles.container}
      showsVerticalScrollIndicator={false}
      showsHorizontalScrollIndicator={false}
    >
      <View>
        <Text style={styles.title}>Promotions</Text>
        <FlatList
          horizontal
          showsHorizontalScrollIndicator={false}
          nestedScrollEnabled
          data={promotions}
          renderItem={renderPromos}
          keyExtractor={(item) => item.id.toString()}
        />
      </View>
      <View>
        <Text style={styles.title}>Our Services</Text>
        {services?.map((service) => {
          return (
            <CustomCard
              key={service.id}
              title={service.vehicleServiceName}
              id={service.id}
              price={"Price : Rs." + service.vehicleServicePrice}
              description={service.vehicleServiceDescription}
              imageUri={service.imageURL}
              service={service}
              action={pressActionService}
            />
          );
        })}
      </View>
    </ScrollView>
  );
};

export default HomeScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },
  title: {
    marginTop: 10,
    marginLeft: 15,
    fontSize: 30,
    color: "#03254c",
  },
});
