import React from "react";
import { View } from "react-native";
import Modal from "react-native-modal";

const Popup = (props) => {
  const { visible, children } = props;
  return (
    <View style={{ flex: 1, height: 100 }}>
      <Modal isVisible={visible}>
        <View
          style={{
            margin: 20,
            backgroundColor: "white",
            borderRadius: 10,
            padding: 35,
            alignItems: "center",
            shadowColor: "#000",
            shadowOffset: {
              width: 0,
              height: 2,
            },
            shadowOpacity: 0.25,
            shadowRadius: 4,
            elevation: 5,
          }}
        >
          {children}
        </View>
      </Modal>
    </View>
  );
};

export default Popup;
