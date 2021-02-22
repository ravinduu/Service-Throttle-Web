import React, { createContext, useContext, useReducer } from "react";

export const DataLayerContext = createContext();

export const DataLayer = (props) => {
  return (
    <DataLayerContext.Provider
      value={useReducer(props.reducer, props.initialState)}
    >
      {props.children}
    </DataLayerContext.Provider>
  );
};

export const useDataLayerValue = () => useContext(DataLayerContext);
