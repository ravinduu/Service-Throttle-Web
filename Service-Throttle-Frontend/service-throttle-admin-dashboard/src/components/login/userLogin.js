import React, { useState } from "react";
import axios from "axios";

const api = "http://localhost:8081/st";
let users;
let token = localStorage.getItem("token");
let authAxios = axios.create({
  baseURL: api,
  headers: {
    Authorization: `Bearer ${token}`,
  },
});

const state = {
  username: "",
  password: "",
};

export const userLogin = async (username, password) => {
  state.username = username;
  state.password = password;

  try {
    const res = await axios.post(`${api}/login`, state);
    token = res.data.jwttoken;
    localStorage.setItem("token", token);
    console.log(state);
  } catch (err) {}

  console.log(token);
};
