const fetchUsers = async (authAxios, type) => {
  try {
    const res = await authAxios.get(`/users/${type}`);

    const _users = await res.data;
    console.log(_users);
    return _users;
  } catch (error) {
    console.log(error);
  }
};

const _editUser = async (authAxios, data) => {
  try {
    const res = await authAxios.post(`/account/update`, data);
    const _users = await res.data;
    return _users;
  } catch (error) {
    console.log(error);
  }
};

const _deleteUser = async (authAxios, username) => {
  try {
    const res = await authAxios.delete(`/delete/${username}`);
    return res;
  } catch (error) {
    console.log(error);
  }
};

const _addUser = async (authAxios, user) => {
  try {
    console.log("insss");
    const res = await authAxios.post(`/register/admin`, user);
    return res;
  } catch (error) {
    console.log(error);
  }
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

export function addUser(authAxios, user) {
  return _addUser(authAxios, user);
}
