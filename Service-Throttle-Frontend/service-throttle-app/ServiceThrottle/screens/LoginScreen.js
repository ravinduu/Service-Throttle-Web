import React from "react";
import { KeyboardAvoidingView, StyleSheet, View } from "react-native";
import { Button, Image, Input } from "react-native-elements";

const LoginScreen = () => {
  const signIn = () => {
    console.log("signInnn");
  };

  return (
    <KeyboardAvoidingView style={styles.container} behavior="height">
      <Image
        source={require("../images/1024.png")}
        style={{ width: 150, height: 150 }}
      />
      <View>
        <Input
          inputContainerStyle={styles.inputContainer}
          placeholder="Username"
          autoFocus
          type="username"
        />
        <Input
          inputContainerStyle={styles.inputContainer}
          placeholder="Password"
          secureTextEntry
          type="password"
        />
      </View>
      <Button
        buttonStyle={{
          backgroundColor: "#104a8e",
        }}
        containerStyle={styles.button}
        onPress={signIn}
        title="Login"
      />
      <Button
        buttonStyle={{
          borderColor: "#104a8e",
        }}
        titleStyle={{ color: "#104a8e" }}
        containerStyle={styles.button}
        type="outline"
        title="Sign Up"
      />
    </KeyboardAvoidingView>
  );
};

export default LoginScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  inputContainer: {
    width: 330,
    height: 50,
    borderWidth: 1,
    borderColor: "#104a8e",
    borderRadius: 5,
    paddingLeft: 10,
  },
  button: {
    paddingTop: 5,
    width: 200,
    height: 50,
  },
});
