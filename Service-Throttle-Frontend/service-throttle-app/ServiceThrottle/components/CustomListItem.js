import React from "react";
import { View, Text } from "react-native";
import { Icon, ListItem } from "react-native-elements";

const CustomListItem = (props) => {
  const { _key, icon, type, title, onpress, subTitle } = props;

  const action = () => {
    onpress(title);
  };

  if (subTitle) {
    return (
      <View>
        <ListItem key={_key} onPress={action}>
          <Icon name={icon} type={type} />
          <ListItem.Content>
            <ListItem.Title>{title}</ListItem.Title>
            <ListItem.Subtitle>{subTitle}</ListItem.Subtitle>
          </ListItem.Content>
        </ListItem>
      </View>
    );
  }

  return (
    <View>
      <ListItem key={_key} onPress={action}>
        <Icon name={icon} type={type} />
        <ListItem.Content>
          <ListItem.Title>{title}</ListItem.Title>
        </ListItem.Content>
      </ListItem>
    </View>
  );
};

export default CustomListItem;
