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

export function getUsers(authAxios, type) {
  return fetchUsers(authAxios, type);
}

export function editUser(authAxios, data) {
  return _editUser(authAxios, data);
}
