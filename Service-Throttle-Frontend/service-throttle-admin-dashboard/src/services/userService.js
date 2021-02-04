const fetchUsers = async (authAxios, type) => {
  const res = await authAxios.get(`/users/${type}`);

  const _users = await res.data;
  console.log(_users);
  return _users;
};

const _editUser = async (authAxios, data) => {
  const res = await authAxios.post(`/account`, data);
  const _users = await res.data;

  return _users;
};

const _deleteUser = async (authAxios, username) => {
  console.log("insss");
  console.log(username);
  const res = await authAxios.delete(`/delete/${username}`);
  return username;
};

export function getUsers(authAxios, type) {
  return fetchUsers(authAxios, type);
}

export function editUser(authAxios, data) {
  return _editUser(authAxios, data);
}

export function deleteUser(authAxios, username) {
  return _deleteUser(authAxios, username);
}
