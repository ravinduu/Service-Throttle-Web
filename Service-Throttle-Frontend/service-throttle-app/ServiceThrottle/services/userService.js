const fetchUsers = async (authAxios, username) => {
  try {
    const res = await authAxios.get(`/account/${username}`);
    const user = res.data;
    return user;
  } catch (error) {
    console.log(error);
  }
};

const _editCurrentUser = async (authAxios, userData) => {
  try {
    const res = await authAxios.post(`/account`, userData);
    const user = res.data;
    return user;
  } catch (error) {
    console.log(error);
  }
};

export function getCurrentUser(authAxios, username) {
  return fetchUsers(authAxios, username);
}

export function editCurrentUser(authAxios, userData) {
  return _editCurrentUser(authAxios, userData);
}
